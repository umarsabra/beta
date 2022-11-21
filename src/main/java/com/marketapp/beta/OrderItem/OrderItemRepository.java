package com.marketapp.beta.OrderItem;

import com.marketapp.beta.Dto.CartItemOut;
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
            value = "UPDATE order_item SET quantity = ?1, physical_quantity = ?2, total_price = ?3, total_cost = ?4 WHERE id = ?5",
            nativeQuery = true
    )
    void updateOrderItem(Integer quantity, Integer physicalQuantity, Float totalPrice, Float totalCost, Long orderItemId);


    @Query("SELECT NEW OrderItem(" +
            "OI.id, " +
            "I.title, " +
            "I.barcode, " +
            "OI.priceBarcode, " +
            "OI.quantity, " +
            "OI.physicalQuantity, " +
            "I.pricePerUnit, " +
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
            "OI.priceBarcode, " +
            "OI.quantity, " +
            "OI.physicalQuantity, " +
            "I.pricePerUnit, " +
            "OI.totalPrice, " +
            "OI.totalCost, " +
            "I.id, " +
            "OI.orderId) " +
            "FROM OrderItem OI " +
            "INNER JOIN Item I ON OI.itemId=I.id " +
            "WHERE OI.orderId = ?1"
    )
    List<OrderItem> findOrderItemsWithItemsInformation(Long orderId);


    @Query("SELECT NEW com.marketapp.beta.Dto.CartItemOut(" +
            "I.id, " +
            "OI.orderId, " +
            "OI.id, " +
            "I.barcode, " +
            "OI.quantity, " +
            "OI.totalPrice) " +
            "FROM OrderItem OI " +
            "INNER JOIN Item I ON OI.itemId = I.id " +
            "WHERE OI.orderId = ?1")
    List<CartItemOut> findCartItems(Long orderId);





}
