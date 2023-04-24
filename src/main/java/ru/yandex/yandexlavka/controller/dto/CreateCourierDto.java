package ru.yandex.yandexlavka.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.dto.CourierType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourierDto {

    @JsonProperty(value = "courier_type",required = true)
    private CourierType type;
    @JsonProperty(required = true)
    private List<Integer> regions;
    @JsonProperty(value = "working_hours",required = true)
    private List<String> workingHours;
}
