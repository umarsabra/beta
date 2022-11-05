package com.marketapp.beta.OrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    void addOrderItemToOrder(OrderItem orderItem){
        orderItemRepository.save(orderItem);

    }
    void removeOrderItemFromOrder(Long orderId, Long orderItemId){
        orderItemRepository.deleteOrderItemFromOrder(orderId, orderItemId);

    }
    void updateOrderItemQuantity(Long orderItemId, Integer quantity){
        orderItemRepository.updateQuantity(quantity, orderItemId);
    }

    List<OrderItem> findOrderItems(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
}
