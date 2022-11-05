package com.marketapp.beta.Order;

import com.marketapp.beta.Item.Item;
import com.marketapp.beta.Item.ItemRepository;
import com.marketapp.beta.OrderItem.OrderItem;
import com.marketapp.beta.OrderItem.OrderItemRepository;
import com.marketapp.beta.Request.OrderItemRequest;
import com.marketapp.beta.Request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
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
    void createPendingOrder(OrderItemRequest orderItemRequest){
        //Get the item barcode and quantity form the request body
        Long orderItemBarcode = orderItemRequest.getBarcode();
        Integer orderItemQuantity = orderItemRequest.getQuantity();

        //Get item id, price, cost per item, quantity
        Item item = itemRepository.findItemByBarcode(orderItemBarcode);
        Float totalCost = item.getCostPerItem() * orderItemQuantity;
        Float totalPrice = item.getPrice() * orderItemQuantity;


        //Check if order item quantity is less than the inventory

        //Create order object with pending status
        Order order = new Order(
                "pending",
                totalPrice,
                totalCost
        );

        Order savedOrder = orderRepository.save(order);

        //Create order item object
        OrderItem orderItem = new OrderItem(
                orderItemQuantity,
                totalPrice,
                totalCost,
                item.getId(),
                savedOrder.getId()

        );

        orderItemRepository.save(orderItem);


        }


    @Transactional
    void checkoutOrder(Long orderId){

        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isPresent()){

            List<OrderItem> orderItems = orderItemRepository.findByOrderId(order.get().getId());

            for (OrderItem orderItem : orderItems) {

                Integer orderItemQuantity = orderItem.getQuantity();
                Long orderItemId = orderItem.getId();
                itemRepository.sellItem(orderItemQuantity, orderItemId);

            }

            orderRepository.updateOrderStatus("completed", orderId);

        }



    }




    Optional<Order> getOrder(Long orderId){
        return orderRepository.findById(orderId);
    }


    List<Order> getCompleteOrders(){
        return orderRepository.findByStatus("completed");
    }

    Order getCurrentPendingOrder(){
        return orderRepository.findByStatus("pending").get(0);
    }

}
