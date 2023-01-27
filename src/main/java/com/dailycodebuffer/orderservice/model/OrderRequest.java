package com.dailycodebuffer.orderservice.model;




import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.dailycodebuffer.orderservice.entity.PaymentWay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderRequest {
	
	private Long productId;
	private Long quantity;
	private PaymentWay paymentMode;
	private Instant orderDate;
	private String orderStatus;
	private Long amount;
}
