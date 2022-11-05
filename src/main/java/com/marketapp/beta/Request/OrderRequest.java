package com.marketapp.beta.Request;

public class OrderRequest {

    public Long barcode;
    public Long quantity;

    public OrderRequest(Long barcode, Long quantity) {
        this.barcode = barcode;
        this.quantity = quantity;
    }

    public Long getBarcode() {
        return barcode;
    }

    public Long getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "barcode=" + barcode +
                ", quantity=" + quantity +
                '}';
    }
}
