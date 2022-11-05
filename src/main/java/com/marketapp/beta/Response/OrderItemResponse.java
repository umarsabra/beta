package com.marketapp.beta.Response;

public class OrderItemResponse {
    public String title;
    public Long barcode;

    public Integer quantity;
    public Float totalPrice;
    public Float totalCost;

    public OrderItemResponse(String title, Long barcode, Integer quantity, Float totalPrice, Float totalCost) {
        this.title = title;
        this.barcode = barcode;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
    }
}
