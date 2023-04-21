package ru.yandex.yandexlavka.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.dto.CompleteOrderDto;
import ru.yandex.yandexlavka.controller.dto.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;

import java.util.List;

@Service
//TODO fill
public class OrderServiceImpl implements OrderService{

    @Override
    public List<OrderDto> createOrders(List<CreateOrderDto> newOrders) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public OrderDto getOrderById(Long orderId) {
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<OrderDto> getOrders(Integer offset, Integer limit) {
        return null;
    }

    @Override
    public List<OrderDto> completeOrders(List<CompleteOrderDto> completedOrders) {
        return null;
    }
}
