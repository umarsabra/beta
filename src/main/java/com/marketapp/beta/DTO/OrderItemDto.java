package com.marketapp.beta.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDto {
    public Long id;
    public String title;
    public Integer quantity;
    public Float price;
    public Float totalPrice;
    public Float totalCost;
}
