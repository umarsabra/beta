package com.marketapp.beta.Order;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class OrderItem {
    @Id
    @SequenceGenerator(
            name = "order_item_sequence",
            sequenceName = "order_item_sequence",
            allocationSize =  1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_item_sequence"
    )
    private Long id;
    private Integer quantity;
    private Float totalPrice;
    private Float totalCost;
    private Long itemId;
    private Long orderId;
}
