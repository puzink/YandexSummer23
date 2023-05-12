package ru.yandex.yandexlavka.repository;

import org.springframework.data.repository.Repository;
import ru.yandex.yandexlavka.controller.request.CreateCourierDto;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CourierRepository extends Repository<Courier, Long> {

    Optional<Courier> getCourierById(Long id);
    List<Courier> getCouriers(int offset, int limit);
    List<Courier> getCouriersByIdInOrderById(Collection<Long> ids);
    List<CourierDto> addCouriers(List<CreateCourierDto> newCouriers);

}
