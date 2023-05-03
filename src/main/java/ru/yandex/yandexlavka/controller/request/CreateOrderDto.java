package ru.yandex.yandexlavka.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDto {
    @JsonProperty(required = true)
    private String weight;
    @JsonProperty(value = "regions", required = true)
    private Integer region;

    @JsonProperty(value = "delivery_hours",required = true)
    private List<String> deliveryHours;
    @JsonProperty(required = true)
    private Integer cost;

}
