package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.error.NotFoundException;
import ru.yandex.yandexlavka.controller.request.CreateCourierDto;
import ru.yandex.yandexlavka.controller.response.CourierMetaInfoResponse;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;
import ru.yandex.yandexlavka.entity.Order;
import ru.yandex.yandexlavka.repository.CourierRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
//TODO fill
@Transactional
public class CouriersServiceImpl implements CourierService{

    private final CourierRepository courierRepository;

    @Autowired
    public CouriersServiceImpl(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Optional<Courier> getCourierById(Long id){
        return courierRepository.getCourierById(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<CourierDto> addCouriers(List<CreateCourierDto> couriers){
        return courierRepository.addCouriers(couriers);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Courier> getCouriers(Integer limit, Integer offset){
        return courierRepository.getCouriers(offset,limit);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public CourierMetaInfoResponse getCourierMetaInfo(Long courierId,
                                                      LocalDateTime startDate,
                                                      LocalDateTime endDate) {
        Courier courier = courierRepository.getCourierById(courierId)
                .orElseThrow(() -> new NotFoundException("Courier is not found."));

        List<Order> filteredByDateCompletedOrders = courier.getCompletedOrders()
                .stream()
                .filter(o -> o.getCompletedTime().compareTo(startDate) >= 0
                            && o.getCompletedTime().compareTo(endDate) < 0)
                .toList();
        Integer rating = calculateCourierRating(courier,
                filteredByDateCompletedOrders,
                startDate,
                endDate);
        Long earning = calculateCourierEarning(courier, filteredByDateCompletedOrders);

        return new CourierMetaInfoResponse(courierId,
                courier.getType(),
                courier.getRegions(),
                courier.getWorkingHours(),
                rating,
                earning);
    }

    private Integer calculateCourierRating(Courier courier,
                                           List<Order> orders,
                                           LocalDateTime startDate,
                                           LocalDateTime endDate) {
        if(orders.isEmpty()){
            return null;
        }
        int hoursCount = (int) startDate.until(endDate, ChronoUnit.HOURS);
        return orders.size() / hoursCount * courier.getType().getRatingCoef();
    }

    private Long calculateCourierEarning(Courier courier,
                                         List<Order> orders) {
        if(orders.isEmpty()){
            return null;
        }
        long ordersCost = orders.stream()
                .mapToInt(Order::getCost)
                .reduce(0, Integer::sum);
        return ordersCost * courier.getType().getEarningCoef();
    }
}
