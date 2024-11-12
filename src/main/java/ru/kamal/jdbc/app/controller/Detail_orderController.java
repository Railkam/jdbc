package ru.kamal.jdbc.app.controller;

import org.springframework.web.bind.annotation.*;
import ru.kamal.jdbc.app.entity.Detail_order;
import ru.kamal.jdbc.app.repository.Detail_orderRepository;

@RestController
@RequestMapping("/order")
public class Detail_orderController {
    private final Detail_orderRepository detail_orderRepository;

    public Detail_orderController(Detail_orderRepository detailOrderRepository) {
        detail_orderRepository = detailOrderRepository;
    }


    @PostMapping("/{order_id}/detail")
    public Long createDetail_order (@PathVariable Long order_id, @RequestBody Detail_order detail_order) {
        return  detail_orderRepository.createDetail_order(order_id, detail_order);
    }
}
