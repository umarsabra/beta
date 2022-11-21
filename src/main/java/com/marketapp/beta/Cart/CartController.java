package com.marketapp.beta.Cart;


import com.marketapp.beta.Dto.Cart;
import com.marketapp.beta.Dto.CartItemIn;
import com.marketapp.beta.Dto.CartItemOut;
import com.marketapp.beta.Exception.InvalidBarcodeException;
import com.marketapp.beta.Exception.ItemNotFoundException;
import com.marketapp.beta.Item.Item;
import com.marketapp.beta.Item.ItemService;
import com.marketapp.beta.Order.Order;
import com.marketapp.beta.Order.OrderService;
import com.marketapp.beta.OrderItem.OrderItem;
import com.marketapp.beta.OrderItem.OrderItemRepository;
import com.marketapp.beta.OrderItem.OrderItemService;
import com.marketapp.beta.Utils.BarcodeAnalyser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderItemRepository orderItemRepository;

    @PostMapping
    public ResponseEntity<Cart> handlePendingOrder(@RequestBody @Valid CartItemIn cartItemIn) throws ItemNotFoundException, InvalidBarcodeException {
        BarcodeAnalyser barcodeAnalyser = new BarcodeAnalyser(cartItemIn.getBarcode());
        String priceBarcode = barcodeAnalyser.getPriceBarcode();
        String itemBarcode = barcodeAnalyser.getBarcode();
        Integer physicalQuantity = cartItemIn.getQuantity();
        Optional<Item> requestItem = itemService.getItemByBarcode(itemBarcode);

        if(requestItem.isEmpty()) throw new ItemNotFoundException("item with barcode: " + priceBarcode + " was not found");

        Float actualQuantity = barcodeAnalyser.getWeight(requestItem.get().getUnitType());

        requestItem.get().setPriceBarcode(priceBarcode);

        Optional<Order> pendingOrder = orderService.getPendingOrder();

        if(pendingOrder.isPresent()){

            List<OrderItem> pendingOrderItems = orderItemService.getOrderItemsWithItemsInformation(pendingOrder.get().getId());
            boolean itemExists = false;

            for (OrderItem orderItem: pendingOrderItems) {
                if (Objects.equals(orderItem.getPriceBarcode(), priceBarcode)) {
                    itemExists = true;
                    List<OrderItem> updatedOrderItems = orderItemService.updatePendingOrderItemQuantity(actualQuantity, physicalQuantity, requestItem.get(), orderItem);
                    List<CartItemOut> cartItems = orderItemRepository.findCartItems(pendingOrder.get().getId());


                    Order updatedOrder = orderService.getOrderById(orderItem.getOrderId());
                    updatedOrder.setOrderItems(updatedOrderItems);
                    Cart cart = Cart.builder()
                            .orderId(updatedOrder.getId())
                            .totalPrice(updatedOrder.getTotalPrice())
                            .cartItem(cartItems)
                            .build();
                    return new ResponseEntity<>(cart, HttpStatus.OK);
                }
            }

            if (!itemExists) {
                List<OrderItem> updateOrderItems = orderItemService.addOrderItemToPendingOrder(priceBarcode, actualQuantity, physicalQuantity, requestItem.get(), pendingOrder.get().getId());
                List<CartItemOut> cartItems = orderItemRepository.findCartItems(pendingOrder.get().getId());
                Order updateOrder = orderService.getOrderById(pendingOrder.get().getId());
                updateOrder.setOrderItems(updateOrderItems);
                Cart cart = Cart.builder()
                        .orderId(updateOrder.getId())
                        .totalPrice(updateOrder.getTotalPrice())
                        .cartItem(cartItems)
                        .build();
                return new ResponseEntity<>(cart, HttpStatus.CREATED);
            }

        }
        Order order = orderService.createPendingOrder(actualQuantity, physicalQuantity, priceBarcode, requestItem.get());
        List<CartItemOut> cartItems = orderItemRepository.findCartItems(order.getId());
        Cart cart = Cart.builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .cartItem(cartItems)
                .build();

        return new ResponseEntity<>(cart, HttpStatus.CREATED);

    }

    @GetMapping
    Cart getPendingOrder(){
        return orderService.getPendingOrder2();
    }

}
