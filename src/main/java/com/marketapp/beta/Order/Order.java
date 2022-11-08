package com.marketapp.beta.Order;


import com.marketapp.beta.OrderItem.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@ToString
@NoArgsConstructor
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



    public Order(String status, Float totalPrice, Float totalCost) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
    }
}
