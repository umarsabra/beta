package com.marketapp.beta.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(
            value = "UPDATE order_item oi SET oi.quantity = ?1 WHERE oi.id = ?2",
            nativeQuery = true
    )
    void updateQuantity(Integer quantity, Long orderItemId);

    @Query(
            value = "DELETE FROM order_item oi WHERE oi.order_id = ?1 AND oi.id = ?2",
            nativeQuery = true
    )
    void deleteOrderItemFromOrder(Long orderId, Long orderItemId);

    List<OrderItem> findByOrderId(Long orderId);
}
