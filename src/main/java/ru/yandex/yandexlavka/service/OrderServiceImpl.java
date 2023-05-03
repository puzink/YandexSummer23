package ru.yandex.yandexlavka.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.request.CompleteOrderDto;
import ru.yandex.yandexlavka.controller.request.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;
import ru.yandex.yandexlavka.repository.OrderRepository;

import java.util.List;

@Service
//TODO fill
@Transactional
@NoArgsConstructor
public class OrderServiceImpl implements OrderService{

//    private final OrderRepository orderRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<OrderDto> createOrders(List<CreateOrderDto> newOrders) {
        return orderRepository.createOrders(newOrders);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Order> getOrders(Integer offset, Integer limit) {
        return orderRepository.getOrders(offset,limit);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<OrderDto> completeOrders(List<CompleteOrderDto> completedOrders) {
        return null;
    }
}
