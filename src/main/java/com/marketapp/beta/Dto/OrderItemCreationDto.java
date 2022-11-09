package com.marketapp.beta.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemCreationDto implements Serializable {
    @NotNull
    public Long barcode;
    @NotNull
    @Min(1)
    public Integer quantity;
}
