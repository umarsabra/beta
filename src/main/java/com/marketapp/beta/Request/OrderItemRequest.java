package com.marketapp.beta.Request;

public class OrderItemRequest {
    public Long barcode;
    public Integer quantity;

    public OrderItemRequest(Long barcode, Integer quantity) {
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public Long getBarcode() {
        return barcode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderItemRequest{" +
                "barcode=" + barcode +
                ", quantity=" + quantity +
                '}';
    }
}
