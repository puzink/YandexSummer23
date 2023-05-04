package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.controller.request.CompleteOrderRequest;
import ru.yandex.yandexlavka.controller.request.CreateOrderRequest;
import ru.yandex.yandexlavka.controller.error.NotFoundException;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;
import ru.yandex.yandexlavka.entity.converter.OrderConverter;
import ru.yandex.yandexlavka.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders")
//TODO fill
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public List<OrderDto> createOrders(@RequestBody CreateOrderRequest newOrders){
        System.out.println("Hi");
        return orderService.createOrders(newOrders.getOrders());
    }

    @GetMapping("{orderId}")
    public OrderDto getOrderById(@PathVariable Long orderId){

        Optional<Order> order = orderService.getOrderById(orderId);
        return new OrderConverter().toDto(order.orElseThrow(NotFoundException::new));
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestParam(required = false,defaultValue = "0") Integer offset,
                                    @RequestParam(required = false,defaultValue = "1") Integer limit){

        List<Order> orders = orderService.getOrders(offset, limit);
        return null;
    }

    @PostMapping("complete")
    public List<OrderDto> completeOrders(@RequestBody CompleteOrderRequest completedOrders){
        return orderService.completeOrders(completedOrders.getCompleteInfo());
    }

}
