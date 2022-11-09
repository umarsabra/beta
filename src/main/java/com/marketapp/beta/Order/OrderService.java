package com.marketapp.beta.Order;


import com.marketapp.beta.Dto.OrderItemCreationDto;


import com.marketapp.beta.Item.Item;

import com.marketapp.beta.OrderItem.OrderItem;
import com.marketapp.beta.OrderItem.OrderItemRepository;


import com.marketapp.beta.OrderItem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;



    public Order createPendingOrder(OrderItemCreationDto orderItemRequest,Item item) {

        Integer orderItemQuantity = orderItemRequest.getQuantity();


        Float totalCost = item.getCostPerItem() * orderItemQuantity;
        Float totalPrice = item.getPrice() * orderItemQuantity;


        Order newPendingOrder = orderRepository.save(new Order(
                "pending",
                totalPrice,
                totalCost
        ));


        OrderItem orderItem =  OrderItem.builder()
                .itemId(item.getId())
                .itemBarcode(item.getBarcode())
                .itemPrice(item.getPrice())
                .itemTitle(item.getTitle())
                .quantity(orderItemQuantity)
                .totalPrice(totalPrice)
                .totalCost(totalCost)
                .orderId(newPendingOrder.getId())
                .build();

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        newPendingOrder.setOrderItems(List.of(savedOrderItem));


        return newPendingOrder;

        }


    public Optional<Order> getPendingOrder() {
        return orderRepository.findPendingOrder();
    }


    public void updateOrderDetails(Long orderId) {
        Float totalPrice = 0f;
        Float totalCost = 0f;

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);

        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
            totalCost += orderItem.getTotalCost();
        }

        orderRepository.updateOrderTotalPriceAndTotalCost(totalPrice, totalCost, orderId);

    }

    public Order createEmptyPendingOrder(){
        Order order = Order.builder()
                .status("pending")
                .totalPrice(0f)
                .totalCost(0f)
                .totalCost(0f)
                .build();

        return orderRepository.save(order);
    }


    public Order getOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId);
    }

    public Order checkoutPendingOrder(Long orderId) {

        List<OrderItem> orderItems = orderItemRepository.findOrderItemsWithItemsInformation(orderId);
        Float totalPrice = 0f;
        Float totalCost = 0f;

        for (OrderItem orderItem: orderItems){
            totalPrice+= orderItem.getTotalPrice();
            totalCost+= orderItem.getTotalCost();
        }
        orderRepository.checkoutOrder(totalPrice, totalCost, orderId);

       return Order.builder()
                .id(orderId)
                .totalPrice(totalPrice)
                .totalCost(totalCost)
                .status("completed")
                .orderItems(orderItems)
                .build();


    }


    public List<Order> getCompletedOrders() {
        return orderRepository.findOrderByStatus("completed");
    }


}
