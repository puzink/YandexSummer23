package ru.yandex.yandexlavka.dto;

import lombok.Data;
import ru.yandex.yandexlavka.controller.request.CreateCourierDto;

import java.util.List;

@Data
public class CourierDto {

    private Long id;
    private CourierType type;
    private List<Integer> regions;
    private List<String> workingHours;

    public CourierDto(Long id, CreateCourierDto courier) {
        this(id,courier.getType(), courier.getRegions(), courier.getWorkingHours());
    }

    public CourierDto(Long id, CourierType type, List<Integer> regions, List<String> workingHours) {
        this.id = id;
        this.type = type;
        this.regions = regions;
        this.workingHours = workingHours;
    }
}