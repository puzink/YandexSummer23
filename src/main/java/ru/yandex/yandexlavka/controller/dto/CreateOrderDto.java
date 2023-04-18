package ru.yandex.yandexlavka.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    @JsonProperty(required = true)
    private Double weight;
    @JsonProperty(required = true)
    private Integer regions;

    @JsonProperty(value = "delivery_hours",required = true)
    private List<String> deliveryHours;
    @JsonProperty(required = true)
    private Integer cost;

}
