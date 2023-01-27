package com.dailycodebuffer.orderservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dailycodebuffer.orderservice.model.OrderRequest;
import com.dailycodebuffer.orderservice.model.OrderResponse;
import com.dailycodebuffer.orderservice.service.OrderServiceImp;



@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderServiceImp orderServiceImp;

	

	@PostMapping("/placeOrder")
	public ResponseEntity<String> placeOrder(@RequestBody OrderRequest OrderEntity) {
		String order = orderServiceImp.placeOrder(OrderEntity);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	
	@GetMapping("/getOrderDetails/{orderId}")
	public ResponseEntity<OrderResponse> placeOrder(@PathVariable("orderId") Long orderId) {
		
		return new ResponseEntity<>( orderServiceImp.getOrderDetailsById(orderId), HttpStatus.OK);
	}
	
	@GetMapping("/data")
	public  ResponseEntity example(){
		
		return  new ResponseEntity<>("Strrfdsgfye",HttpStatus.OK);
	}

}
