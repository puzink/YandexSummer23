package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderDto {
    @JsonProperty("order_id")
    private Long id;
    private Double weight;
    private Integer regions;

    @JsonProperty("delivery_hours")
    private List<String> deliveryHours;
    private Integer cost;
    @JsonProperty("completed_time")
    private String completedTime;

}
