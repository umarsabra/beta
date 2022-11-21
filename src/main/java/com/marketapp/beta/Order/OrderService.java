package com.marketapp.beta.Order;





import com.marketapp.beta.Dto.Cart;
import com.marketapp.beta.Item.Item;

import com.marketapp.beta.Item.UnitType;
import com.marketapp.beta.OrderItem.OrderItem;
import com.marketapp.beta.OrderItem.OrderItemRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;



    public Order createPendingOrder(Integer actualQuantity, Integer physicalQuantity, String priceBarcode,Item item) {

        Float totalCost = item.getCostPerUnit() * actualQuantity;
        float totalPrice = item.getPricePerUnit() * actualQuantity;

        if(item.getUnitType() == UnitType.L){
            Float gramPrice = item.getPricePerUnit() / 1000;
            totalPrice = actualQuantity * gramPrice;
        }


        Order newPendingOrder = orderRepository.save(new Order(
                "pending",
                totalPrice,
                totalCost
        ));


        OrderItem orderItem =  OrderItem.builder()
                .itemId(item.getId())
                .itemBarcode(item.getBarcode())
                .priceBarcode(priceBarcode)
                .itemPrice(item.getPricePerUnit())
                .itemTitle(item.getTitle())
                .quantity(actualQuantity)
                .physicalQuantity(physicalQuantity)
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

    public Cart getPendingOrder2() {
        return orderRepository.findPendingOrder2();
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
