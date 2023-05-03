package ru.yandex.yandexlavka.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompleteOrderDto {
    @JsonProperty(value = "courier_id",required = true)
    private Long courierId;

    @JsonProperty(value = "order_id",required = true)
    private Long orderId;

    @JsonProperty(value = "complete_time",required = true)
    private String completeTime;

}
