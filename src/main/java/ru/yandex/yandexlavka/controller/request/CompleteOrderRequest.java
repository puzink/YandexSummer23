package ru.yandex.yandexlavka.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CompleteOrderRequest {
    @JsonProperty(value = "complete_info",required = true)
    private List<CompleteOrderDto> completeInfo;
}
