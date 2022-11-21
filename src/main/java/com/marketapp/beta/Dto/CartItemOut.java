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
public class CartItemOut implements Serializable {
    private Long itemId;
    private Long orderId;
    private Long orderItemId;
    private String priceBarcode;
    private Integer quantity;
    private Float totalPrice;
}
