package com.dailycodebuffer.orderservice.service;

import java.util.Map;

import com.dailycodebuffer.orderservice.model.OrderRequest;




public interface OrderService {

	public String placeOrder(OrderRequest orderRequest);
}
