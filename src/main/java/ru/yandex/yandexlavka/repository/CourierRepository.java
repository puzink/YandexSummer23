package ru.yandex.yandexlavka.repository;

import org.springframework.data.repository.Repository;
import ru.yandex.yandexlavka.controller.dto.CreateCourierDto;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;

import java.util.List;

public interface CourierRepository extends Repository<Courier, Long> {

    Courier getCourierById(Long id);
    List<Courier> getCouriers(int offset, int limit);
    List<CourierDto> addCouriers(List<CreateCourierDto> newCouriers);

}
