package com.marketapp.beta.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cart implements Serializable {
    private Float totalPrice;
    private Long orderId;
    private List<CartItemOut> cartItem;

    public Cart(Float totalPrice, Long orderId) {
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }
}
