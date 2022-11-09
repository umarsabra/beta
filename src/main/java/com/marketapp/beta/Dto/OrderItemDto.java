package com.marketapp.beta.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderItemDto {
    public Long id;
    public String title;
    public Integer quantity;
    public Float price;
    public Float totalPrice;
    public Float totalCost;
}
