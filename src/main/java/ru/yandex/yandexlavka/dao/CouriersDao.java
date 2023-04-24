package ru.yandex.yandexlavka.dao;

import ru.yandex.yandexlavka.controller.dto.CreateCourierDto;
import ru.yandex.yandexlavka.dto.CourierDto;

import java.util.List;

public interface CouriersDao {

    List<CourierDto> addCouriers(List<CreateCourierDto> couriers);
    List<CourierDto> addCouriers(List<CreateCourierDto> couriers, int batchSize);

}
