package ru.yandex.yandexlavka.repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.request.CreateOrderDto;
import ru.yandex.yandexlavka.dao.OrderDaoImpl;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;

import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository{

    @PersistenceContext
    private Session session;

    private final OrderDaoImpl orderDao;

    @Autowired
    public OrderRepositoryImpl(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Order getOrderById(Long id) {
        return session.get(Order.class, id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Order> getOrders(int offset, int limit) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Order> allOrdersCriteria = cb.createQuery(Order.class);

        Root<Order> root = allOrdersCriteria.from(Order.class);
        Path<Long> idAttribute = root.get(root.getModel().getId(Long.class));

        allOrdersCriteria.orderBy(cb.asc(idAttribute));
        return session.createQuery(allOrdersCriteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<Order> getOrdersByIdInOrderById(Collection<Long> ids) {
        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        JpaCriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = query.from(Order.class);
        Path<Long> idAttribute = root.get(root.getModel().getId(Long.class));
        query.where(idAttribute.in(ids));
        return session.createQuery(query)
                .getResultList();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<OrderDto> createOrders(List<CreateOrderDto> newOrders) {
        return orderDao.createOrders(newOrders);
    }

}
