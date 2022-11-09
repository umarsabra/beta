package com.marketapp.beta.Exception;

public class PendingOrderNotFoundException extends Exception {
    public PendingOrderNotFoundException(String there_is_no_pending_orders) {
        super(there_is_no_pending_orders);
    }
}
