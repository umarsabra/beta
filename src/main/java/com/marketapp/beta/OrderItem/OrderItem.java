package com.marketapp.beta.OrderItem;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
    @JsonProperty("title")
    public String itemTitle;
    @Transient
    @JsonIgnore
    public Long itemBarcode;
    private Integer quantity;
    @Transient
    @JsonProperty("price")
    private Float itemPrice;

    @JsonProperty("total_price")
    private Float totalPrice;

    @JsonProperty("total_cost")
    private Float totalCost;
    @JsonProperty("item_id")
    private Long itemId;

    @JsonIgnore
    private Long orderId;

    public OrderItem(Integer quantity, Float price, Float totalPrice, Float totalCost, Long itemId, Long orderId) {
        this.quantity = quantity;
        this.itemPrice = price;
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
