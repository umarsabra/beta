package com.marketapp.beta.Order;

import com.marketapp.beta.DTO.OrderDetailsDto;
import com.marketapp.beta.DTO.OrderItemCreationDto;
import com.marketapp.beta.DTO.OrderItemDto;

import com.marketapp.beta.Item.Item;
import com.marketapp.beta.Item.ItemRepository;
import com.marketapp.beta.OrderItem.OrderItem;
import com.marketapp.beta.OrderItem.OrderItemRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ItemRepository itemRepository;



    @Transactional
    public OrderDetailsDto createPendingOrder(OrderItemCreationDto orderItemRequest) {
        //Get the item barcode and quantity form the request body
        Long orderItemBarcode = orderItemRequest.getBarcode();
        Integer orderItemQuantity = orderItemRequest.getQuantity();


        //Get item id, price, cost per item, quantity
        Item item = itemRepository.findItemByBarcode(orderItemBarcode);
        Float totalCost = item.getCostPerItem() * orderItemQuantity;
        Float totalPrice = item.getPrice() * orderItemQuantity;


        //Create order object with pending status
        Order order = new Order(
                "pending",
                totalPrice,
                totalCost
        );

        Order newPendingOrder = orderRepository.save(order);

        //Create order item object
        OrderItem orderItem = new OrderItem(
                orderItemQuantity,
                item.getPrice(),
                totalPrice,
                totalCost,
                item.getId(),
                newPendingOrder.getId()

        );

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);


        OrderItemDto orderItemResponse = new OrderItemDto(
                savedOrderItem.getId(),
                item.getTitle(),
                savedOrderItem.getQuantity(),
                item.getPrice(),
                savedOrderItem.getTotalPrice(),
                savedOrderItem.getTotalCost()
        );

        return new OrderDetailsDto(
                newPendingOrder.getId(),
                newPendingOrder.getTotalPrice(),
                newPendingOrder.getTotalCost(),
                List.of(orderItemResponse)
        );


        }


    public Optional<Order> getPendingOrder() {
        return orderRepository.findPendingOrder();
    }

    public OrderDetailsDto updateOrderDetails(Long orderId) {
        Float totalPrice = 0f;
        Float totalCost = 0f;

        List<OrderItem> orderItems = orderItemRepository.getOrderItems(orderId);

        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
            totalCost += orderItem.getTotalCost();
        }

        //Get orderItems
        List<OrderItem> updatedOrderItems = orderItemRepository.getOrderItems(orderId);
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        for (OrderItem orderItem : updatedOrderItems) {
            OrderItemDto orderItemDto = new OrderItemDto(
                    orderItem.getId(),
                    orderItem.getTitle(),
                    orderItem.getQuantity(),
                    orderItem.getPrice(),
                    orderItem.getTotalPrice(),
                    orderItem.getTotalCost()

            );
            orderItemDtoList.add(orderItemDto);
        }



        return new OrderDetailsDto(
                orderId,
               totalPrice,
               totalCost,
                orderItemDtoList
        );


    }
}
