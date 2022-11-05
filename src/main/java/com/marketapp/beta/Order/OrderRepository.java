package com.marketapp.beta.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> getOrderByStatus(String status);


    @Query(
            value = "UPDATE orders o SET o.status = ?1 WHERE o.id = ?2",
            nativeQuery = true
    )
    void updateOrderStatus(String status, Long orderId);

    List<Order> findByStatus(String status);
}
