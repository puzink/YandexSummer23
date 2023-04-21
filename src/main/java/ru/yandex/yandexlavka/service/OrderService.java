package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.controller.dto.CompleteOrderDto;
import ru.yandex.yandexlavka.controller.dto.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> createOrders(List<CreateOrderDto> newOrders);

    OrderDto getOrderById(Long orderId);

    List<OrderDto> getOrders(Integer offset, Integer limit);

    List<OrderDto> completeOrders(List<CompleteOrderDto> completedOrders);
}
