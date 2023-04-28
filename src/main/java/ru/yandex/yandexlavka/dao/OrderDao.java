package ru.yandex.yandexlavka.dao;

import ru.yandex.yandexlavka.controller.dto.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;

import java.util.List;

public interface OrderDao {
    List<OrderDto> createOrders(List<CreateOrderDto> newOrders);
    List<OrderDto> createOrders(List<CreateOrderDto> newOrders, int batchSize);

}
