package com.marketapp.beta.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDetailsDto {
    public Long id;
    public Float totalPrice;
    public Float totalCost;
    public List<OrderItemDto> orderItems;
}
