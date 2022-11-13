package com.marketapp.beta.Item;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@NoArgsConstructor
@Builder
@Data
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
    private String barcode;
    @NotNull
    private String title;
    @NotNull
    private UnitType unitType;
    @NotNull
    private Float pricePerUnit;
    @Min(1)
    @NotNull
    private Integer quantity;
    @NotNull
    private Float costPerQuantity;
    private Float weightPerQuantity;


    //Relations
    //Transients
    @Transient
    private Float netWeight;
    @Transient
    private Float costPerUnit;

    public Float getNetWeight(){
        return quantity * weightPerQuantity;
    }
    public Float getCostPerUnit() {
        return costPerQuantity/quantity;
    }


    @Transient
    @JsonIgnore
    private String priceBarcode;

}
