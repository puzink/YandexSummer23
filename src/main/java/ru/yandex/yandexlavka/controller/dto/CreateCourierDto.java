package ru.yandex.yandexlavka.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.yandex.yandexlavka.dto.CourierType;

import java.util.List;

@Data
public class CreateCourierDto {

    @JsonProperty("courier_type")
    private CourierType type;
    private List<Integer> regions;
    @JsonProperty("working_hours")
    private List<String> workingHours;
}
