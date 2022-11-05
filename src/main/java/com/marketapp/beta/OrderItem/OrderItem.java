package com.marketapp.beta.OrderItem;


import com.marketapp.beta.Item.Item;
import com.marketapp.beta.Order.Order;
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


    @ManyToOne(
            targetEntity = Item.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            referencedColumnName = "id",
            name = "item_id",
            foreignKey = @ForeignKey(name = "order_item_item_id")

    )
    private Long itemId;


    @ManyToOne(
            targetEntity = Order.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            referencedColumnName = "id",
            name = "order_id",
            foreignKey = @ForeignKey(name = "order_item_order_id")
    )
    private Long orderId;

    public OrderItem(Integer quantity, Float totalPrice, Float totalCost, Long itemId, Long orderId) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.itemId = itemId;
        this.orderId = orderId;
    }


}
