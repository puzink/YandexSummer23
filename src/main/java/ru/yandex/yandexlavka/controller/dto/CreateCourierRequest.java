package ru.yandex.yandexlavka.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateCourierRequest {
    @JsonProperty("couriers")
    private List<CreateCourierDto> couriers;
}
