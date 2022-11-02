package com.marketapp.beta.Item;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@Entity
@ToString
@NoArgsConstructor
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
    Boolean isWeight;
    @Transient
    Float costPerItem;

}
