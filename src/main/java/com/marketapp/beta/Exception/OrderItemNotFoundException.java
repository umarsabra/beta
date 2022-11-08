package com.marketapp.beta.Exception;

public class OrderItemNotFoundException extends Exception {
    public OrderItemNotFoundException(String not_found) {
        super(not_found);
    }
}
