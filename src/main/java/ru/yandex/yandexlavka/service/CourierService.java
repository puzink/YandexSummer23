package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.controller.request.CreateCourierDto;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;

import java.util.List;

public interface CourierService {

    Courier getCourierById(Long id);

    List<CourierDto> addCouriers(List<CreateCourierDto> couriers);

    List<Courier> getCouriers(Integer limit, Integer offset);


}
