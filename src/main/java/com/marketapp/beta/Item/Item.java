package com.marketapp.beta.Item;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@ToString
@Data
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
    private String barcode;
    @Transient
    private String priceBarcode;
    @NotNull
    private Float price;
    @Min(1)
    private Integer quantity;
    @NotNull
    @JsonProperty("total_cost")
    private Float totalCost;
    @JsonProperty("cost_per_item")
    @Transient
    private Float costPerItem;

    @NotNull
    private Boolean isWeightItem;

    public Item() {
    }

    public Item(String title, String barcode, Float price, Integer quantity, Float totalCost) {
        this.title = title;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity;
        this.totalCost = totalCost;

    }


    public Float getCostPerItem() {
        return totalCost/quantity;
    }
    public Float getPrice(){
        return price;
    }
}
