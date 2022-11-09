package com.marketapp.beta.Order;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketapp.beta.OrderItem.OrderItem;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;
    private String status;
    @JsonProperty("total_price")
    private Float totalPrice;
    @JsonProperty("total_cost")
    private Float totalCost;

    @Transient
    @JsonProperty("order_items")
    private List<OrderItem> orderItems;



    public Order(String status, Float totalPrice, Float totalCost) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
    }

}
