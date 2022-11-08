package com.marketapp.beta.OrderItem;

import com.marketapp.beta.DTO.OrderDetailsDto;
import com.marketapp.beta.Order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;




@Service
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderService orderService;

    //Add item to pending order returns order
    @Transactional
    public OrderDetailsDto addOrderItemToPendingOrder(OrderItem newOrderItem, Long pendingOrderId) {
        orderItemRepository.save(newOrderItem);
        return orderService.updateOrderDetails(pendingOrderId);

    }


    public List<OrderItem> findOrderItems(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
    public List<OrderItem> getOrderItems(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }



    public void updateOrderItem(Integer quantity, Float totalPrice, Float totalCost, Long orderItemId) {
         orderItemRepository.updateOrderItem(quantity, totalPrice, totalCost, orderItemId);
    }
}
