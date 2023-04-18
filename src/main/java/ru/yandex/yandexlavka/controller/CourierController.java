package ru.yandex.yandexlavka.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.controller.dto.CreateCourierRequest;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.dto.OrderDto;

import java.util.List;

@RestController
@RequestMapping("couriers")
//TODO fill
public class CourierController {

    @GetMapping
    public List<CourierDto> getCouriers(@RequestParam(required = false,defaultValue = "1") Integer limit,
                            @RequestParam(required = false,defaultValue = "0") Integer offset){
        System.out.println("Hi");
        return null;
    }

    @GetMapping("{courierId}")
    public CourierDto getCourierById(@PathVariable Long courierId){
        System.out.println("Hi");
        return null;
    }

    @PostMapping
    public List<CourierDto> addCouriers(@RequestBody CreateCourierRequest createCourierRequest){
        System.out.println("Hi");
        return null;
    }


}
