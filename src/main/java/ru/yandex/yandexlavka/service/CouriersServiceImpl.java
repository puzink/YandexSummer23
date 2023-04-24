package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.dto.CreateCourierDto;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;
import ru.yandex.yandexlavka.repository.CourierRepository;

import java.util.List;

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
    public Courier getCourierById(Long id){
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
        return null;
    }
}
