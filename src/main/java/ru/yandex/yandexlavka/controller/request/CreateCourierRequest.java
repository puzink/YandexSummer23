package ru.yandex.yandexlavka.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateCourierRequest {
    @JsonProperty(value = "couriers",required = true)
    private List<CreateCourierDto> couriers;
}
