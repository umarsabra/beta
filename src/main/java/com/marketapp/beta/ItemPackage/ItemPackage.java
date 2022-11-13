package com.marketapp.beta.ItemPackage;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marketapp.beta.Item.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPackage {
    public enum PackageType {
        PACKAGE,
        CONTAINER
    }

    @Id
    @SequenceGenerator(
            name = "package_sequence",
            sequenceName = "package_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "package_sequence"
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private PackageType packageType;
    private String barcode;
    @JsonIgnore
    @OneToOne(mappedBy = "itemPackage", cascade = CascadeType.ALL)
    private Item item;
    private Integer quantity;
    private Float weightPerPackage;
    private Float pricePerPackage;
    private Float costPerPackage;



}
