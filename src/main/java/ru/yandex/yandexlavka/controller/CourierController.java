package ru.yandex.yandexlavka.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.controller.dto.CreateCourierRequest;
import ru.yandex.yandexlavka.dto.CourierDto;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("couriers")
//TODO fill
public class CourierController {

    @GetMapping
    public void getCouriers(@RequestParam(required = false) Integer limit,
                            @RequestParam(required = false) Integer offset){
        System.out.println("Hi");

    }

    @GetMapping("{courierId}")
    public void getCourierById(@PathVariable Long courierId){
        System.out.println("Hi");
    }

    @PostMapping
    public List<CourierDto> addCouriers(@RequestBody CreateCourierRequest createCourierRequest){
        System.out.println("Hi");
        return null;
    }

}
