package com.marketapp.beta.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marketapp.beta.Item.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCreationDot implements Serializable {
    @NotNull
    private String barcode;
    @NotNull
    private String title;
    @NotNull
    @JsonProperty("unit_type")
    @Enumerated(value = EnumType.STRING)
    private UnitType unitType;
    @NotNull
    @JsonProperty("price_per_unit")
    private Float pricePerUnit;
    @NotNull
    private Integer quantity;
    @NotNull
    @JsonProperty("cost_per_quantity")
    private Float costPerQuantity;
    @NotNull
    @JsonProperty("weight_per_quantity")
    private Float weightPerQuantity;
    @JsonProperty("net_weight")
    private Float netWeight;

    public Float getNetWeight() {
        return quantity * weightPerQuantity;
    }
}
