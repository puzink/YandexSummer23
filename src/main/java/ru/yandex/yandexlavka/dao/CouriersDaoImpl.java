package ru.yandex.yandexlavka.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.controller.dto.CreateCourierDto;
import ru.yandex.yandexlavka.dto.CourierDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CouriersDaoImpl implements CouriersDao {

    private final JdbcTemplate jdbcTemplate;
    private final static Integer DEFAULT_BATCH_SIZE = 100;

    @Autowired
    public CouriersDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CourierDto> addCouriers(List<CreateCourierDto> couriers) {
        return addCouriers(couriers, DEFAULT_BATCH_SIZE);
    }

    @Override
    public List<CourierDto> addCouriers(List<CreateCourierDto> couriers, int batchSize) {
        List<CourierDto> insertedCouriers = new ArrayList<>();
        int loopStepCounts = couriers.size() / batchSize + (couriers.size() % batchSize == 0 ? 0 : 1);
        for (int i = 0; i < loopStepCounts; ++i) {
            List<Object> params = new ArrayList<>();
            List<CreateCourierDto> couriersToInsert =
                    couriers.subList(i * batchSize, Math.min(couriers.size(), (i + 1) * batchSize));
            couriersToInsert.forEach(c -> {
                params.add(c.getType().name());
                params.add(c.getRegions().toArray(new Integer[0]));
                params.add(c.getWorkingHours().toArray(new String[0]));
            });

            List<Long> ids = jdbcTemplate.queryForList(createAddCouriersSqlStatement(couriersToInsert.size()),
                    Long.class,
                    params.toArray()
            );

            for (int j = 0; j < ids.size(); ++j) {
                insertedCouriers.add(new CourierDto(ids.get(j), couriersToInsert.get(j)));
            }

        }
        return insertedCouriers;
    }

    private String createAddCouriersSqlStatement(int courierCount) {
        return String.format("select add_couriers(%s)",
                String.join(",", Collections.nCopies(courierCount, "(?,?,?)"))
        );
    }
}
