package com.marketapp.beta.Item;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    Long id;
    String title;
    Float price;
    Long barcode;

    public Item(String title, Float price, Long barcode) {
        this.title = title;
        this.price = price;
        this.barcode = barcode;
    }
}
