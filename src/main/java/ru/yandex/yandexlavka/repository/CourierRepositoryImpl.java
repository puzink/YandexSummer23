package ru.yandex.yandexlavka.repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.request.CreateCourierDto;
import ru.yandex.yandexlavka.dao.CouriersDao;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    public Optional<Courier> getCourierById(Long id) {
        return Optional.ofNullable(session.get(Courier.class, id));
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

    @Override
    public List<Courier> getCouriersByIdInOrderById(Collection<Long> ids) {
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        JpaCriteriaQuery<Courier> query = criteriaBuilder.createQuery(Courier.class);
        Root<Courier> root = query.from(Courier.class);
        Path<Long> idAttribute = root.get(root.getModel().getId(Long.class));
        query.where(idAttribute.in(ids));
        return session.createQuery(query)
                .getResultList();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<CourierDto> addCouriers(List<CreateCourierDto> newCouriers) {
        return courierDao.addCouriers(newCouriers);
    }
}
