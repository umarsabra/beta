package com.marketapp.beta.OrderItem;


import com.marketapp.beta.Dto.OrderItemCreationDto;
import com.marketapp.beta.Item.Item;

import com.marketapp.beta.Order.Order;
import com.marketapp.beta.Order.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderService orderService;


    public List<OrderItem> addOrderItemToPendingOrder(OrderItemCreationDto orderItemCreationDto, Item item, Long pendingOrderId) {
        Integer quantity = orderItemCreationDto.getQuantity();
        Float totalPrice = item.getPrice() * quantity;
        Float totalCost = item.getCostPerItem() * quantity;
        OrderItem newOrderItem = new OrderItem(
                quantity,
                totalPrice,
                totalCost,
                item.getId(),
                pendingOrderId
        );

        orderItemRepository.save(newOrderItem);
        orderService.updateOrderDetails(pendingOrderId);
        return orderItemRepository.findOrderItemsWithItemsInformation(pendingOrderId);

    }

    public List<OrderItem> updatePendingOrderItemQuantity(Integer newQuantity, Item requestItem, OrderItem orderItem){
        Float totalPrice = requestItem.getPrice() * newQuantity;
        Float totalCost = requestItem.getCostPerItem() * newQuantity;

        orderItemRepository.updateOrderItem(newQuantity, totalPrice, totalCost, orderItem.getId());

        orderService.updateOrderDetails(orderItem.getOrderId());
        return orderItemRepository.findOrderItemsWithItemsInformation(orderItem.getOrderId());

    }

    public List<OrderItem> getOrderItemsWithItemsInformation(Long orderItemId){
        return orderItemRepository.findOrderItemsWithItemsInformation(orderItemId);
    }

    public OrderItem getOrderItemWithItemInformation(Long orderItemId){
        return orderItemRepository.findOrderItemWithItemInformation(orderItemId);
    }

    public Order removeOrderItemFromPendingOrder(Long pendingOrderId, Long orderItemId){
        orderItemRepository.deleteOrderItemFromOrder(pendingOrderId, orderItemId);
        orderService.updateOrderDetails(pendingOrderId);
        List<OrderItem> updatedOrderItems = orderItemRepository.findOrderItemsWithItemsInformation(pendingOrderId);
        Order updatedOrder = orderService.getOrderById(pendingOrderId);
        updatedOrder.setOrderItems(updatedOrderItems);
        return updatedOrder;
    }


    public Optional<OrderItem> getOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId);
    }
}
