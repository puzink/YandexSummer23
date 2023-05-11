package ru.yandex.yandexlavka.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompleteOrderDto {
    @JsonProperty(value = "courier_id",required = true)
    @NotNull
    private Long courierId;

    @JsonProperty(value = "order_id",required = true)
    @NotNull
    private Long orderId;

    @JsonProperty(value = "complete_time",required = true)
    @NotNull
    private LocalDateTime completeTime;
}
