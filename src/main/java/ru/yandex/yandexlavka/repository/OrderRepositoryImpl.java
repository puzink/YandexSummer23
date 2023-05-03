package ru.yandex.yandexlavka.repository;

import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.request.CreateOrderDto;
import ru.yandex.yandexlavka.dao.OrderDaoImpl;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;

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
        JpaCriteriaQuery<Order> allOrdersCriteria =
                session.getCriteriaBuilder().createQuery(Order.class);
        return session.createQuery(allOrdersCriteria)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<OrderDto> createOrders(List<CreateOrderDto> newOrders) {
        return orderDao.createOrders(newOrders);
    }
}
