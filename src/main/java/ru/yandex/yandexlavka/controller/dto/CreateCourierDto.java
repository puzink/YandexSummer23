package ru.yandex.yandexlavka.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.yandex.yandexlavka.dto.CourierType;

import java.util.List;

@Data
public class CreateCourierDto {

    @JsonProperty(value = "courier_type",required = true)
    private CourierType type;
    @JsonProperty(required = true)
    private List<Integer> regions;
    @JsonProperty(value = "working_hours",required = true)
    private List<String> workingHours;
}
