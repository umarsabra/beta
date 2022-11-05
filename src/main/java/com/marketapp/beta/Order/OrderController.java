package com.marketapp.beta.Order;


import com.marketapp.beta.OrderItem.OrderItemService;
import com.marketapp.beta.Request.OrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping
    void createOrder(@RequestBody OrderItemRequest orderRequest){
        orderService.createPendingOrder(orderRequest);

    }

    @GetMapping
    List<Order> getCompleteOrders(){
        return orderService.getCompleteOrders();
    }

    @GetMapping(value = "/pending")
    Order getCurrentPendingOrder(){
        return orderService.getCurrentPendingOrder();

    }

}
