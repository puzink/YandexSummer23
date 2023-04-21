package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.controller.dto.CreateCourierDto;
import ru.yandex.yandexlavka.dto.CourierDto;

import java.util.List;

public interface CourierService {

    CourierDto getCourierById(Long id);

    List<CourierDto> addCouriers(List<CreateCourierDto> couriers);

    List<CourierDto> getCouriers(Integer limit, Integer offset);


}
