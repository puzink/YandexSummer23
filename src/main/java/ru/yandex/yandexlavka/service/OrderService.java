package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.controller.request.CompleteOrderDto;
import ru.yandex.yandexlavka.controller.request.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDto> createOrders(List<CreateOrderDto> newOrders);

    Optional<Order> getOrderById(Long orderId);

    List<Order> getOrders(Integer offset, Integer limit);

    List<OrderDto> completeOrders(List<CompleteOrderDto> completedOrders);
}
