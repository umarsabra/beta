package com.marketapp.beta.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    @Modifying(clearAutomatically = true)
    @Query(
            value = "DELETE FROM order_item oi WHERE oi.order_id = ?1 AND oi.id = ?2",
            nativeQuery = true
    )
    void deleteOrderItemFromOrder(Long orderId, Long orderItemId);

    List<OrderItem> findByOrderId(Long orderId);


    @Modifying(clearAutomatically = true)

    @Query(
            value = "UPDATE order_item SET quantity = ?1, total_price = ?2, total_cost = ?3 WHERE id = ?4",
            nativeQuery = true
    )
    void updateOrderItem(Integer quantity, Float totalPrice, Float totalCost, Long orderItemId);



    @Query("SELECT NEW OrderItem(" +
            "OI.id, " +
            "I.title, " +
            "I.barcode, " +
            "OI.quantity, " +
            "I.price, " +
            "OI.totalPrice, " +
            "OI.totalCost, " +
            "I.id, " +
            "OI.id) " +
            "FROM OrderItem OI " +
            "INNER JOIN Item I ON OI.itemId=I.id " +
            "WHERE OI.orderId = ?1"
    )
    List<OrderItem> getOrderItems(Long orderId);

    @Query("SELECT NEW OrderItem(" +
            "OI.id, " +
            "I.title, " +
            "I.barcode, " +
            "OI.quantity, " +
            "I.price, " +
            "OI.totalPrice, " +
            "OI.totalCost, " +
            "I.id, " +
            "OI.id) " +
            "FROM OrderItem OI " +
            "INNER JOIN Item I ON OI.itemId=I.id " +
            "WHERE OI.id = ?1"
    )
    OrderItem findOrderItemOmar(Long orderItemId);

    @Query("SELECT NEW OrderItem(" +
            "OI.id, " +
            "I.title, " +
            "I.barcode, " +
            "OI.quantity, " +
            "I.price, " +
            "OI.totalPrice, " +
            "OI.totalCost, " +
            "I.id, " +
            "OI.orderId) " +
            "FROM OrderItem OI " +
            "INNER JOIN Item I ON OI.itemId=I.id " +
            "WHERE OI.id = ?1"
    )
    OrderItem findOrderItemWithItemInformation(Long orderItemId);

    @Query("SELECT NEW OrderItem(" +
            "OI.id, " +
            "I.title, " +
            "I.barcode, " +
            "OI.quantity, " +
            "I.price, " +
            "OI.totalPrice, " +
            "OI.totalCost, " +
            "I.id, " +
            "OI.orderId) " +
            "FROM OrderItem OI " +
            "INNER JOIN Item I ON OI.itemId=I.id " +
            "WHERE OI.orderId = ?1"
    )
    List<OrderItem> findOrderItemsWithItemsInformation(Long orderId);





}
