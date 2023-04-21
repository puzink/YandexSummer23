package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.controller.dto.CreateCourierRequest;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.service.CouriersServiceImpl;

import java.util.List;

@RestController
@RequestMapping("couriers")
//TODO fill
public class CourierController {

    private final CouriersServiceImpl couriersService;

    @Autowired
    public CourierController(CouriersServiceImpl couriersService) {
        this.couriersService = couriersService;
    }


    @GetMapping
    public List<CourierDto> getCouriers(@RequestParam(required = false,defaultValue = "1") Integer limit,
                            @RequestParam(required = false,defaultValue = "0") Integer offset){
        return couriersService.getCouriers(limit,offset);
    }

    @GetMapping("{courierId}")
    public CourierDto getCourierById(@PathVariable Long courierId){
        return couriersService.getCourierById(courierId);
    }

    @PostMapping
    public List<CourierDto> addCouriers(@RequestBody CreateCourierRequest newCouriers){
        return couriersService.addCouriers(newCouriers.getCouriers());
    }


}
