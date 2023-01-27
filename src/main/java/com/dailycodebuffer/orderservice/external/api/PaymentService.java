package com.dailycodebuffer.orderservice.external.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dailycodebuffer.orderservice.external.exception.CustomExceptionHandleing;
import com.dailycodebuffer.orderservice.external.request.PaymentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@FeignClient("PAYMENT-SERVICE/payment")
@CircuitBreaker(name ="external" ,fallbackMethod = "fallBack")
public interface PaymentService {

	@PostMapping("/doPayment")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);
	
	default CustomExceptionHandleing fallBack(Exception e) {
		throw new CustomExceptionHandleing("Payment_service Not Avaliable", "500");
	}
}
