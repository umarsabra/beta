package com.marketapp.beta.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    Order getOpenOrder(){
        Optional<Order> pendingOrder = orderRepository.getOrderByStatus("pending");

        if (pendingOrder.isPresent()) {
            return  pendingOrder.get();
        }
        else {
            Order newPendingOrder = new Order("pending");
            return orderRepository.save(newPendingOrder);

        }
    }

    Order addItemToOrder(@RequestBody Long itemId){

    }
}
