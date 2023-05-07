package ru.yandex.yandexlavka.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @JsonProperty(value = "orders",required = true)
    @Valid
    private List<CreateOrderDto> orders;

}
