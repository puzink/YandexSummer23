package ru.yandex.yandexlavka.entity.converter;

import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;
import ru.yandex.yandexlavka.entity.TimeInterval;
import ru.yandex.yandexlavka.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.List;

public class OrderConverter implements EntityConverter<Order, OrderDto> {


    @Override
    public Order toEntity(OrderDto dto) {
        LocalDateTime competedTime = dto.getCompletedTime() == null
                ? null
                : LocalDateTime.parse(dto.getCompletedTime());
         return new Order(dto.getId(),
                dto.getWeight(),
                dto.getRegion(),
                dto.getDeliveryHours().stream().map(DateUtils::parse).toList(),
                dto.getCost(),
                 competedTime);
    }

    @Override
    public OrderDto toDto(Order entity) {
        List<String> deliveryHourStrings = entity.getDeliveryHours().stream()
                .map(TimeInterval::toString)
                .toList();
        String completedTimeString = entity.getCompletedTime() == null ? null : entity.getCompletedTime().toString();
        return new OrderDto(entity.getId(),
                entity.getWeight(),
                entity.getRegion(),
                deliveryHourStrings,
                entity.getCost(),
                completedTimeString);
    }
}
