package ru.yandex.yandexlavka.repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.request.CreateCourierDto;
import ru.yandex.yandexlavka.dao.CouriersDao;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;

import java.util.List;

@Repository
@Transactional
public class CourierRepositoryImpl implements CourierRepository{

    @PersistenceContext
    private Session session;

    private final CouriersDao courierDao;

    @Autowired
    public CourierRepositoryImpl(CouriersDao courierDao) {
        this.courierDao = courierDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Courier getCourierById(Long id) {
        return session.get(Courier.class, id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Courier> getCouriers(int offset, int limit) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        return session.createQuery(criteriaBuilder.createQuery(Courier.class))
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<CourierDto> addCouriers(List<CreateCourierDto> newCouriers) {
        return courierDao.addCouriers(newCouriers);
    }
}
