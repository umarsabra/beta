package com.marketapp.beta.Order;

import com.marketapp.beta.Dto.Cart;
import com.marketapp.beta.Dto.SalesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders o WHERE o.status = 'pending' LIMIT 1" , nativeQuery = true)
    Optional<Order> findPendingOrder();

    @Query("SELECT NEW com.marketapp.beta.Dto.Cart(" +
            "O.totalPrice, " +
            "O.id) " +
            "FROM Order O WHERE O.id = 1")
    Cart findPendingOrder2();

    Order findOrderById(Long orderId);

    @Query(
            "SELECT NEW com.marketapp.beta.Dto.SalesDto(SUM(o.totalPrice), SUM(o.totalCost)) FROM Order o WHERE o.id = 1"
    )
    List<SalesDto> getSales();


    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE orders SET total_price = ?1, total_cost = ?2 WHERE id = ?3",
            nativeQuery = true
    )
    void updateOrderTotalPriceAndTotalCost(Float totalPrice, Float totalCost, Long orderId);

    @Modifying(clearAutomatically = true)
    @Query(
            "UPDATE Order o SET o.status = 'completed', o.totalPrice = ?1, o.totalCost = ?2 WHERE o.id = ?3"
    )
    void checkoutOrder(Float totalPrice, Float totalCost, Long orderId);


    List<Order> findOrderByStatus(String status);
}
