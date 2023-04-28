package ru.yandex.yandexlavka.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.controller.dto.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao{

    private final JdbcTemplate jdbcTemplate;
    private final static int DEFAULT_BATCH_SIZE = 100;

    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OrderDto> createOrders(List<CreateOrderDto> newOrders) {
        return createOrders(newOrders, DEFAULT_BATCH_SIZE);
    }

    @Override
    public List<OrderDto> createOrders(List<CreateOrderDto> newOrders, int batchSize) {
        List<OrderDto> result = new ArrayList<>();
        int loopIterations = newOrders.size() / batchSize
                + (newOrders.size() % batchSize != 0 ? 1 : 0);

        for(int i = 0; i< loopIterations; ++i){
            List<CreateOrderDto> ordersToInsert =
                    newOrders.subList(i * batchSize,
                            Math.min(newOrders.size(),(i + 1) * batchSize)
            );

            List<Object> statementParams = new ArrayList<>();
            ordersToInsert.forEach(order -> {
                statementParams.add(order.getCost());
                statementParams.add(order.getRegion());
                statementParams.add(order.getWeight());
                statementParams.add(order.getDeliveryHours().toArray(new String[0]));
            });

            List<Long> ids = jdbcTemplate.queryForList(createOrdersStatement(ordersToInsert.size()),
                    Long.class,
                    statementParams.toArray()
            );

            for(int j = 0; j < ids.size(); ++j){
                result.add(new OrderDto(ids.get(j), ordersToInsert.get(j)));
            }

        }

        return result;
    }

    private String createOrdersStatement(int size) {
        String functionParams =
                String.join(",", Collections.nCopies(size, "(?,?,?,?)"));
        return String.format("select add_orders(%s)", functionParams);
    }
}
