package ru.yandex.yandexlavka.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    private Double weight;
    private Integer regions;

    @JsonProperty("delivery_hours")
    private List<String> deliveryHours;
    private Integer cost;

}
