package com.marketapp.beta.Order;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
@ToString
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
    private Float totalPrice;
    private Float totalCost;

    public Order(String status) {
        this.status = status;
    }
}
