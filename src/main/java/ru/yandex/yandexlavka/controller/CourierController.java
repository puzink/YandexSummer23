package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.controller.request.CreateCourierRequest;
import ru.yandex.yandexlavka.controller.response.CourierMetaInfoResponse;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.entity.Courier;
import ru.yandex.yandexlavka.service.CouriersServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        List<Courier> couriers = couriersService.getCouriers(limit, offset);
        return null;
    }

    @GetMapping("{courierId}")
    public CourierDto getCourierById(@PathVariable Long courierId){
        Optional<Courier> courier = couriersService.getCourierById(courierId);
        return null;
    }

    @PostMapping
    public List<CourierDto> addCouriers(@RequestBody CreateCourierRequest newCouriers){
        List<CourierDto> courierDtos = couriersService.addCouriers(newCouriers.getCouriers());
        return null;
    }

    @GetMapping("/meta-info/{courier_id}")
    public CourierMetaInfoResponse getCourierInfo(
            @PathVariable(name="courier_id") Long courierId,
            @RequestParam(name="startDate") LocalDate startDate,
            @RequestParam(name="endDate") LocalDate endDate){

        return couriersService.getCourierMetaInfo(
                courierId,
                startDate.atStartOfDay(),
                endDate.atStartOfDay()
        );
    }


}
