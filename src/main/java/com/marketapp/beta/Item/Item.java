package com.marketapp.beta.Item;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@ToString
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
    Long id;
    String title;
    Long barcode;
    Float price;
    Integer quantity;
    Float totalCost;
    @Transient
    Float costPerItem;

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
