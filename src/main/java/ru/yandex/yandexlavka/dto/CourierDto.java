package ru.yandex.yandexlavka.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourierDto {

    private Integer id;
    private CourierType type;
    private List<Integer> regions;
    private List<String> workingHours;

}