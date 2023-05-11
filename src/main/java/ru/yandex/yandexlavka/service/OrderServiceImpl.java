package ru.yandex.yandexlavka.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.request.CompleteOrderDto;
import ru.yandex.yandexlavka.controller.request.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Courier;
import ru.yandex.yandexlavka.entity.Order;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OrderRepository;

import java.util.*;

@Service
//TODO fill
@Transactional
@NoArgsConstructor
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;
    private CourierRepository courierRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CourierRepository courierRepository) {
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<OrderDto> createOrders(List<CreateOrderDto> newOrders) {
        return orderRepository.createOrders(newOrders);
    }

//    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Optional<Order> getOrderById(Long orderId) {
        return Optional.ofNullable(orderRepository.getOrderById(orderId));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Order> getOrders(Integer offset, Integer limit) {
        return orderRepository.getOrders(offset,limit);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<Order> completeOrders(List<CompleteOrderDto> ordersToComplete) {
        TreeSet<Long> orderIdsToComplete = ordersToComplete.stream()
                .map(CompleteOrderDto::getOrderId)
                .collect(TreeSet::new, TreeSet::add, TreeSet::addAll);

        Map<Long, Order> existedOrders = orderRepository.getOrdersByIdInOrderById(orderIdsToComplete)
                .stream()
                .collect(HashMap::new, (map, order) -> map.put(order.getId(), order), HashMap::putAll);

        List<Long> courierIdsCompletedOrders = ordersToComplete.stream()
                .map(CompleteOrderDto::getCourierId)
                .toList();

        Map<Long, Courier> existedCouriers =
                courierRepository.getCouriersByIdInOrderById(courierIdsCompletedOrders)
                .stream()
                .collect(HashMap::new, (map, c) -> map.put(c.getId(), c), HashMap::putAll);

        checkNotExistedOrders(orderIdsToComplete, existedOrders);
        checkOrderDuplications(orderIdsToComplete);
        checkAlreadyCompletedOrders(existedOrders);
        checkCouriersExistence(courierIdsCompletedOrders, existedCouriers);

        fillOrdersWithCompletionInfo(ordersToComplete, existedOrders, existedCouriers);

        return existedOrders.values().stream().toList();
    }

    private void checkCouriersExistence(Collection<Long> courierIdsCompletedOrders,
                                        Map<Long, Courier> existedCouriers) {
        Optional<Long> notExistedCourier = courierIdsCompletedOrders.stream()
                .filter(id -> !existedCouriers.containsKey(id))
                .findAny();
        if(notExistedCourier.isPresent()){
            throw new IllegalArgumentException(
                    String.format("Courier does not exist. Id:%d",notExistedCourier.get())
            );
        }
    }

    private void checkAlreadyCompletedOrders(Map<Long, Order> existedOrders) {
        for(Map.Entry<Long,Order> entry : existedOrders.entrySet()){
            if(entry.getValue().isCompleted()){
                throw new IllegalArgumentException(
                        String.format("Order has already been completed. Order id: %d", entry.getKey())
                );
            }
        }
    }

    private void checkNotExistedOrders(Collection<Long> orderIdToComplete,
                                       Map<Long, Order> existedOrderIds) {
        Optional<Long> notExistedOrder = orderIdToComplete.stream()
                .filter(newOrderId -> !existedOrderIds.containsKey(newOrderId))
                .findAny();
        if(notExistedOrder.isPresent()){
            throw new IllegalArgumentException(
                    String.format("Order with (id=(%d)) does not exist.", notExistedOrder.get())
            );
        }
    }

    private void checkOrderDuplications(Collection<Long> ids) {
        TreeSet<Long> metIds = new TreeSet<>();
        for(Long id : ids){
            if(metIds.contains(id)){
                throw new IllegalArgumentException(String.format("Duplicate order has met. Id=%d.", id));
            }
            metIds.add(id);
        }
    }

    private void fillOrdersWithCompletionInfo(List<CompleteOrderDto> ordersToComplete,
                                              Map<Long, Order> existedOrders,
                                              Map<Long, Courier> existedCouriers) {
        ordersToComplete.sort(Comparator.comparingLong(CompleteOrderDto::getOrderId));
        for(CompleteOrderDto completionInfo : ordersToComplete){
            Order order = existedOrders.get(completionInfo.getOrderId());
            order.setCourier(existedCouriers.get(completionInfo.getCourierId()));
            order.setCompletedTime(completionInfo.getCompleteTime());
        }
    }
}
