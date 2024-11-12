package ru.kamal.jdbc.app.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.kamal.jdbc.app.entity.Order;
import ru.kamal.jdbc.app.repository.OrderRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderConrtoller {
    private final OrderRepository orderRepository;

    public OrderConrtoller(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @PostMapping()
    public Long createOrder (@RequestBody Order order) throws IOException {
    return  orderRepository.createOrder(order);
    }

    @GetMapping ("/{id}")
    public Order getOrderById (@PathVariable Long id){
        return orderRepository.getOrderById(id);
    }

    @GetMapping ("/{date_order}/{sum_order}")
    public List<Order> getOrderByDateAndSum (@PathVariable("date_order") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date_order,@PathVariable Long sum_order)
    {
    return orderRepository.getOrderByDateAndSum(date_order, sum_order);
    }

    @GetMapping ("/{date_orderStart}/{date_orderEnd}/{detail_orderName}")
    public List<Order> getOrderByDateAndNotNameDetailOrder (@PathVariable("date_orderStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date_orderStart,
                                             @PathVariable("date_orderEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date_orderEnd,
                                             @PathVariable String detail_orderName)
    {
        return orderRepository.getOrderByDateAndNotNameDetailOrder(date_orderStart, date_orderEnd, detail_orderName);
    }
}
