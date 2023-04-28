package ru.yandex.yandexlavka.repository;

import org.springframework.data.repository.Repository;
import ru.yandex.yandexlavka.controller.dto.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;

import java.util.List;

public interface OrderRepository extends Repository<Order,Long> {

    Order getOrderById(Long id);
    List<Order> getOrders(int offset, int limit);
    List<OrderDto> createOrders(List<CreateOrderDto> newOrders);

}
