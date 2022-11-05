package com.marketapp.beta.Response;

import java.util.List;

public class OrderResponse {
    public Long id;
    public Float totalPrice;
    public Float totalCost;
    public List<OrderItemResponse> orderItems;

    public OrderResponse(Long id, Float totalPrice, Float totalCost, List<OrderItemResponse> orderItems) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.totalCost = totalCost;
        this.orderItems = orderItems;
    }
}
