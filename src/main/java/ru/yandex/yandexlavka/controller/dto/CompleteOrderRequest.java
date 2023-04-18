package ru.yandex.yandexlavka.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CompleteOrderRequest {
    @JsonProperty("complete_info")
    private List<CompleteOrderDto> completeInfo;
}
