package ru.yandex.yandexlavka.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.controller.dto.CreateCourierDto;
import ru.yandex.yandexlavka.dto.CourierDto;

import java.util.List;

@Service
//TODO fill
public class CouriersServiceImpl implements CourierService{

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public CourierDto getCourierById(Long id){
        return null;
    }

    @Override
    public List<CourierDto> addCouriers(List<CreateCourierDto> couriers){
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<CourierDto> getCouriers(Integer limit, Integer offset){
        return null;
    }
}
