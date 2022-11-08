package com.marketapp.beta.Order;


import com.marketapp.beta.DTO.OrderDetailsDto;

import com.marketapp.beta.DTO.OrderItemCreationDto;
import com.marketapp.beta.Exception.ItemNotFoundException;
import com.marketapp.beta.Item.Item;
import com.marketapp.beta.Item.ItemService;
import com.marketapp.beta.OrderItem.OrderItem;
import com.marketapp.beta.OrderItem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @Autowired
    ItemService itemService;


    //create, add, update pending order
    @PostMapping("/pending")
    ResponseEntity<OrderDetailsDto> createOrder(@RequestBody OrderItemCreationDto orderRequest) throws ItemNotFoundException {

        Item requestItem = itemService.getItemByBarcode(orderRequest.getBarcode());
        if(requestItem == null) throw new ItemNotFoundException("item with barcode: " + orderRequest.getBarcode() + " was not found");

        //Get current pending order
        Optional<Order> pendingOrder = orderService.getPendingOrder();

        //If there is a pending order
        if(pendingOrder.isPresent()){
            //Get order items
            List<OrderItem> pendingOrderItems = orderItemService.getOrderItems(pendingOrder.get().getId());

            //Check if the request order item id already in the order
            for (OrderItem orderItem: pendingOrderItems) {

                //If the item exists update the item
                if (Objects.equals(orderItem.getItemId(), requestItem.getId())) {
                    Integer newQuantity = orderRequest.getQuantity();
                    Float totalPrice = requestItem.getPrice() * newQuantity;
                    Float totalCost = requestItem.getCostPerItem() * newQuantity;
                    orderItemService.updateOrderItem(newQuantity, totalPrice, totalCost, orderItem.getId());
                    OrderDetailsDto newOrder = orderService.updateOrderDetails(pendingOrder.get().getId());
                    return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
                    //else add the new item
                } else {
                    Integer quantity = orderRequest.getQuantity();
                    Float totalPrice = requestItem.getPrice() * quantity;
                    Float totalCost = requestItem.getCostPerItem() * quantity;
                    OrderItem newOrderItem = new OrderItem(
                            quantity,
                            totalPrice,
                            totalCost,
                            requestItem.getId(),
                            pendingOrder.get().getId()
                    );

                    OrderDetailsDto orderDetailsDto = orderItemService.addOrderItemToPendingOrder(newOrderItem, pendingOrder.get().getId());
                    return new ResponseEntity<>(orderDetailsDto, HttpStatus.CREATED);
                }

            }
        }
            return new ResponseEntity<>(orderService.createPendingOrder(orderRequest), HttpStatus.CREATED);


    }




}
