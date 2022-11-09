package com.marketapp.beta.Dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SalesDto implements Serializable {

    public double revenue;
    public double expenses;


}
