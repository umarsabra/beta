package com.marketapp.beta.OrderItem;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem  {
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
    @Transient
    public String title;
    @Transient
    public Long barcode;
    private Integer quantity;
    @Transient
    private Float price;
    private Float totalPrice;
    private Float totalCost;
    private Long itemId;

    private Long orderId;

    public OrderItem(Integer quantity, Float price, Float totalPrice, Float totalCost, Long itemId, Long orderId) {
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.itemId = itemId;
        this.orderId = orderId;
    }

    public OrderItem(Integer quantity, Float totalPrice, Float totalCost, Long itemId, Long orderId) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.itemId = itemId;
        this.orderId = orderId;
    }
}
