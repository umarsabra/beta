package com.marketapp.beta.OrderItem;


import com.marketapp.beta.DTO.OrderItemCreationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;


    //Get completed order items
    @GetMapping(path = "/completed/{id}")
    List<OrderItem> getOrderItems(@PathVariable Long id){
        return orderItemService.findOrderItems(id);
    }

    //Add item to pending order
    @PostMapping("/pending")
    void addItemToPendingOrder(@PathVariable Long orderId, @RequestBody OrderItemCreationDto newOrderItem){

    }

    //Delete item from pending order
    @DeleteMapping("/pending/{id}")
    void deleteItemFromOrder(){

    }

}
