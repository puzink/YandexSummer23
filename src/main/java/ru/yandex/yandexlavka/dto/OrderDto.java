package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.controller.dto.CreateOrderDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @JsonProperty("order_id")
    private Long id;
    @JsonProperty("weight")
    private String weight;
    @JsonProperty("regions")
    private Integer region;

    @JsonProperty("delivery_hours")
    private List<String> deliveryHours;
    private Integer cost;
    @JsonProperty("completed_time")
    private String completedTime;

    public OrderDto(Long id, CreateOrderDto order) {
        this(id,order.getWeight(), order.getRegion(), order.getDeliveryHours(), order.getCost());
    }

    public OrderDto(Long id,
                    String weight,
                    Integer region,
                    List<String> deliveryHours,
                    Integer cost) {
        this.id = id;
        this.weight = weight;
        this.region = region;
        this.deliveryHours = deliveryHours;
        this.cost = cost;
    }
}
