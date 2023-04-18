package ru.yandex.yandexlavka.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CompleteOrderDto {
    @JsonProperty("courier_id")
    private Long courierId;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("complete_time")
    //@JsonFormat
    private String completeTime;

}
