package com.marketapp.beta.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders o WHERE o.status = 'pending' LIMIT 1" , nativeQuery = true)
    Optional<Order> findPendingOrder();





}
