package com.marketapp.beta.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    public Long id;
    public Float totalPrice;
    public Float totalCost;
}
