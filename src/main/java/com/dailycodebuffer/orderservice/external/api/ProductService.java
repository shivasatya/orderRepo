package com.dailycodebuffer.orderservice.external.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.dailycodebuffer.orderservice.external.exception.CustomExceptionHandleing;
import com.dailycodebuffer.orderservice.external.exception.CutumerInteral;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@FeignClient("PRODUCT-SERVICE/product")
@CircuitBreaker(name = "es", fallbackMethod = "fallBack")
@Retry(name = "es", fallbackMethod = "fallback")
public interface ProductService {
	
	@GetMapping("reduceQuantity/{id}")
	   ResponseEntity<Void> reduceQuantity(@PathVariable("id") Long orderId,
			   @RequestParam(required = true) Long quality);
	
	
	default void fallBack(Exception e) {
		throw new CustomExceptionHandleing("PRODUCT_service Not Avaliable", "500");
	}
	   }


