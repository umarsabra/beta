package com.marketapp.beta.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemIn implements Serializable {
    private String barcode;
    private Integer quantity;
}
