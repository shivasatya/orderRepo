package com.dailycodebuffer.orderservice.external.request;



import com.dailycodebuffer.orderservice.entity.PaymentWay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
	
	private Long orderId;
	private String paymentMode;
	private String referenceNumber;
	private Long amount;
	}