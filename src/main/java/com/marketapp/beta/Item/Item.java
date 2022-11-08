package com.marketapp.beta.Item;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@ToString
@Builder
@AllArgsConstructor
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Long barcode;

    @NotNull
    private Float price;

    @Min(1)
    private Integer quantity;
    @NotNull
    private Float totalCost;
    @Transient
    private Float costPerItem;

    public Item() {
    }

    public Item(String title, Long barcode, Float price, Integer quantity, Float totalCost) {
        this.title = title;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity;
        this.totalCost = totalCost;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getBarcode() {
        return barcode;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public Float getCostPerItem() {
        return totalCost/quantity;
    }
}
