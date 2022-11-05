package com.marketapp.beta.OrderItem;


import com.marketapp.beta.Request.OrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @GetMapping(path = "/{id}")
    List<OrderItem> getOrderItems(@PathVariable Long id){
        return orderItemService.findOrderItems(id);
    }

    @PostMapping(path = "/{id}")
    void addItemToOrder(@PathVariable Long orderId, @RequestBody OrderItemRequest newOrderItem){

    }

    @DeleteMapping(path = "/{id}/{itemId}")
    void deleteItemFromOrder(){

    }

}
