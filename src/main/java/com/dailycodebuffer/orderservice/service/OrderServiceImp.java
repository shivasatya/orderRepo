package com.dailycodebuffer.orderservice.service;

import java.lang.reflect.Field;
import java.net.URL;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import com.dailycodebuffer.orderservice.entity.OrderEntity;
import com.dailycodebuffer.orderservice.entity.PaymentWay;
import com.dailycodebuffer.orderservice.external.api.PaymentService;
import com.dailycodebuffer.orderservice.external.api.ProductService;
import com.dailycodebuffer.orderservice.external.exception.CustomExceptionHandleing;
import com.dailycodebuffer.orderservice.external.request.PaymentRequest;
import com.dailycodebuffer.orderservice.model.OrderRequest;
import com.dailycodebuffer.orderservice.model.OrderResponse;
import com.dailycodebuffer.orderservice.model.OrderResponse.PaymentResponseDetails;
import com.dailycodebuffer.orderservice.model.OrderResponse.productDetails;
import com.dailycodebuffer.orderservice.repository.OrderDao;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImp implements OrderService {

	public static final String order_message = "OrderService";
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public String placeOrder(OrderRequest orderRequest) {
		String message = "Sucess";
		log.info(order_message, ":placeOrder {}:Your Order Placing Please Wait");
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info(order_message, ":placeOrder {}:Your Order Placed");
		
		OrderEntity orderEntity = OrderEntity.builder().ProductId(orderRequest.getProductId())
				.quantity(orderRequest.getQuantity()).Amount(orderRequest.getAmount())
				.paymentMode(orderRequest.getPaymentMode().name())
				.orderStatus(orderRequest.getOrderStatus()).orderDate(Instant.now()).build();
		orderDao.save(orderEntity);   

		PaymentRequest paymentRequest = PaymentRequest.builder().orderId(orderEntity.getOrderId())
				.amount(orderEntity.getAmount()).referenceNumber(String.valueOf(orderEntity.getProductId()))
				.paymentMode(orderEntity.getPaymentMode())
				.build();
		String orderPayment = null;
		try {	
			orderPayment = "Placed";
			orderEntity.setOrderStatus(orderPayment);
			log.info(order_message, "{} :: order Payment Sucess");
			log.info(order_message, ":placeOrder {}:Your Order is added SucessFully");
			message = "Sucess";
		} catch (Exception e) {
			orderPayment = "Failed";
			orderEntity.setOrderStatus(orderPayment);
			log.info(order_message, "{} :: order Payment failed");
			throw new CustomExceptionHandleing("PaymentFailed", "PAYMENT_FAIED", 500);
		}
		orderDao.save(orderEntity);
		paymentService.doPayment(paymentRequest);
		return message;
	}

	public OrderResponse getOrderDetailsById(Long orderId) {
		log.info(order_message, "{} :: fetching order details...",orderId);
	OrderEntity order =	orderDao.findById(orderId).orElseThrow(() -> new CustomExceptionHandleing("id not found","NOT_FOUND"));
	productDetails pDetails =	restTemplate.getForObject("http://PRODUCT-SERVICE/product/getProductById/" + order.getProductId(), productDetails.class);
    productDetails productDetails = OrderResponse.productDetails.builder()
    	 .productId(order.getProductId())	
         .productName(pDetails.getProductName())
         .price(pDetails.getPrice())
         .quality(pDetails.getQuality())
         .build();
    PaymentResponseDetails paymentResponseDetails =   restTemplate.getForObject("http://PAYMENT-SERVICE/payment/getPaymentDetailsByOrderId/ " + orderId, PaymentResponseDetails.class);
    
    PaymentResponseDetails paymentDetails = OrderResponse.PaymentResponseDetails.builder()
    		        .amount(paymentResponseDetails.getAmount())
    		        .orderId(paymentResponseDetails.getOrderId())
    		        .referenceNumber(paymentResponseDetails.getReferenceNumber())
    		        .paymentMode(paymentResponseDetails.getPaymentMode())
    		        .build();
    		        
	return OrderResponse.builder()
			.quantity(order.getQuantity())
			.amount(order.getAmount())
			.orderDate(order.getOrderDate())
			.orderStatus(order.getOrderStatus())
			.productResponse(productDetails)
			.paymentResponse(paymentDetails)
			.paymentMode(order.getPaymentMode())
			.build();
	}

}
