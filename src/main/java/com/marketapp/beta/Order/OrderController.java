package com.marketapp.beta.Order;




import com.marketapp.beta.Dto.OrderItemCreationDto;
import com.marketapp.beta.Exception.ItemNotFoundException;
import com.marketapp.beta.Exception.OrderItemNotFoundException;
import com.marketapp.beta.Exception.OrderNotFoundException;
import com.marketapp.beta.Exception.PendingOrderNotFoundException;
import com.marketapp.beta.Item.Item;
import com.marketapp.beta.Item.ItemService;
import com.marketapp.beta.OrderItem.OrderItem;
import com.marketapp.beta.OrderItem.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping
    ResponseEntity<List<Order>> getCompletedOrders(){
        return new ResponseEntity<>(orderService.getCompletedOrders(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    ResponseEntity<Order> getCompletedOrder(@PathVariable Long orderId) throws OrderNotFoundException {
        Order order = orderService.getOrderById(orderId);
        if(order == null){
            throw new OrderNotFoundException("order with id: " + orderId + " was not found");
        }
        List<OrderItem> orderItems = orderItemService.getOrderItemsWithItemsInformation(orderId);
        order.setOrderItems(orderItems);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/pending/checkout")
    ResponseEntity<Order> checkoutPendingOrder() throws PendingOrderNotFoundException {
        Optional<Order> currentPendingOrder = orderService.getPendingOrder();
        if(currentPendingOrder.isEmpty()) throw new PendingOrderNotFoundException("there is no pending orders");
        Order completedOrder = orderService.checkoutPendingOrder(currentPendingOrder.get().getId());
        itemService.sell(completedOrder.getOrderItems());
        return new ResponseEntity<>(completedOrder, HttpStatus.OK);
    }

    @PostMapping("/pending")
    ResponseEntity<Order> handlePendingOrder(@RequestBody @Valid OrderItemCreationDto orderRequest) throws ItemNotFoundException {

        Item requestItem = itemService.getItemByBarcode(orderRequest.getBarcode());
        if(requestItem == null) throw new ItemNotFoundException("item with barcode: " + orderRequest.getBarcode() + " was not found");

        Optional<Order> pendingOrder = orderService.getPendingOrder();

        if(pendingOrder.isPresent()){

            List<OrderItem> pendingOrderItems = orderItemService.getOrderItemsWithItemsInformation(pendingOrder.get().getId());
            boolean itemExists = false;

            for (OrderItem orderItem: pendingOrderItems) {
                if (Objects.equals(orderItem.getItemId(), requestItem.getId())) {
                    itemExists = true;
                    List<OrderItem> updatedOrderItems = orderItemService.updatePendingOrderItemQuantity(orderRequest.getQuantity(), requestItem, orderItem);
                    Order updatedOrder = orderService.getOrderById(orderItem.getOrderId());
                    updatedOrder.setOrderItems(updatedOrderItems);
                    return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
                }
            }

            if (!itemExists) {
                List<OrderItem> updateOrderItems = orderItemService.addOrderItemToPendingOrder(orderRequest, requestItem, pendingOrder.get().getId());
                Order updateOrder = orderService.getOrderById(pendingOrder.get().getId());
                updateOrder.setOrderItems(updateOrderItems);
                return new ResponseEntity<>(updateOrder, HttpStatus.CREATED);
            }

        }
        return new ResponseEntity<>(orderService.createPendingOrder(orderRequest, requestItem), HttpStatus.CREATED);

    }

    @GetMapping("/pending")
    ResponseEntity<Order> getPendingOrder() {
        Optional<Order> order = orderService.getPendingOrder();
        if (order.isPresent()){
            List<OrderItem> pendingOrderItems = orderItemService.getOrderItemsWithItemsInformation(order.get().getId());

            Order foundOrder = order.get();
            foundOrder.setOrderItems(pendingOrderItems);
            return new ResponseEntity<>(foundOrder, HttpStatus.OK);
        }
        Order emptyOrder = orderService.createEmptyPendingOrder();
        return new ResponseEntity<>(emptyOrder, HttpStatus.OK);

    }

    @DeleteMapping("/pending/{orderItemId}")
    ResponseEntity<Order> deleteOrderItem(@PathVariable Long orderItemId) throws OrderItemNotFoundException{
        Optional<OrderItem> orderItem = orderItemService.getOrderItem(orderItemId);
        if (orderItem.isPresent()){
            Order updatedOrder = orderItemService.removeOrderItemFromPendingOrder(orderItem.get().getOrderId(),orderItemId);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } throw new OrderItemNotFoundException("not found");
    }




}
