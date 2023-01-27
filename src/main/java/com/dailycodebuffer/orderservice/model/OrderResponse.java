package com.dailycodebuffer.orderservice.model;

import java.time.Instant;

import com.dailycodebuffer.orderservice.entity.PaymentWay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderResponse {
	private productDetails productResponse;
	private Long quantity;
	private String paymentMode;
	private Instant orderDate;
	private String orderStatus;
	private Long amount;
	private PaymentResponseDetails paymentResponse;
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class productDetails {
		private Long productId;
		private String productName;
		private Long price;
		private Long quality;

	}
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class PaymentResponseDetails {
		
		private Long orderId;
		private String paymentMode;
		private String referenceNumber;
		private Long amount;
	}

}


