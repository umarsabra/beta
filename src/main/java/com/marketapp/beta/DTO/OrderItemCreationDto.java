package com.marketapp.beta.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemCreationDto {
    public Long barcode;
    public Integer quantity;
}
