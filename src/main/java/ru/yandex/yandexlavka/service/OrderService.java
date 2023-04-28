package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.controller.dto.CompleteOrderDto;
import ru.yandex.yandexlavka.controller.dto.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;

import java.util.List;

public interface OrderService {

    List<OrderDto> createOrders(List<CreateOrderDto> newOrders);

    Order getOrderById(Long orderId);

    List<Order> getOrders(Integer offset, Integer limit);

    List<OrderDto> completeOrders(List<CompleteOrderDto> completedOrders);
}
