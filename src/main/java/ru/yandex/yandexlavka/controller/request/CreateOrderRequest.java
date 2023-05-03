package ru.yandex.yandexlavka.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @JsonProperty(value = "orders",required = true)
    private List<CreateOrderDto> orders;

}
