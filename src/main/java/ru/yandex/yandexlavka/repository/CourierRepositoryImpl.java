package ru.yandex.yandexlavka.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.dto.CreateCourierDto;
import ru.yandex.yandexlavka.entity.Courier;

import java.util.List;

@Repository
@Transactional
public class CourierRepositoryImpl implements CourierRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Courier getCourierById(Long id) {

        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Courier> getCouriers(int offset, int limit) {
        return null;
    }

    @Override
    public List<Courier> addCouriers(List<CreateCourierDto> newCouriers) {
        return null;
    }
}
