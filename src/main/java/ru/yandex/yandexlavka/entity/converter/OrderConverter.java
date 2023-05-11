package ru.yandex.yandexlavka.entity.converter;

import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;

public class OrderConverter implements EntityConverter<Order, OrderDto> {


    @Override
    public Order toEntity(OrderDto dto) {
         return new Order(dto.getId(),
                dto.getWeight(),
                dto.getRegion(),
                dto.getDeliveryHours(),
                dto.getCost(),
                 null,
                 dto.getCompletedTime());
    }

    @Override
    public OrderDto toDto(Order entity) {
        return new OrderDto(entity.getId(),
                entity.getWeight(),
                entity.getRegion(),
                entity.getDeliveryHours(),
                entity.getCost(),
                entity.getCompletedTime());
    }
}
