package ru.yandex.yandexlavka.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.controller.dto.CompleteOrderRequest;
import ru.yandex.yandexlavka.controller.dto.CreateOrderRequest;
import ru.yandex.yandexlavka.dto.OrderDto;

import java.util.List;

@RestController
@RequestMapping("orders")
//TODO fill
public class OrderController {

    @PostMapping
    public List<OrderDto> createOrders(@RequestBody CreateOrderRequest newOrders){
        System.out.println("Hi");
        return null;
    }

    @GetMapping("{orderId}")
    public OrderDto getOrderById(@PathVariable Long orderId){

        return null;
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestParam(required = false,defaultValue = "0") Integer offset,
                                    @RequestParam(required = false,defaultValue = "1") Integer limit){

        return null;
    }

    @PostMapping("complete")
    public List<OrderDto> completeOrders(@RequestBody CompleteOrderRequest completedOrders){
        return null;
    }

}
