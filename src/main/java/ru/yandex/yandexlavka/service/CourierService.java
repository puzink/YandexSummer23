package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.controller.request.CreateCourierDto;
import ru.yandex.yandexlavka.controller.response.CourierMetaInfoResponse;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourierService {

    Optional<Courier> getCourierById(Long id);

    List<CourierDto> addCouriers(List<CreateCourierDto> couriers);

    List<Courier> getCouriers(Integer limit, Integer offset);

    CourierMetaInfoResponse getCourierMetaInfo(Long courierId, LocalDateTime startDate, LocalDateTime endDate);
}
