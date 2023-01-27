package com.dailycodebuffer.orderservice.external.exception;

import feign.FeignException.InternalServerError;
import lombok.Data;

@Data
public class CustomExceptionHandleing  extends  RuntimeException   {
	
	private String errorCode;
	private int  status;
	
	public CustomExceptionHandleing(String message,String errorCode,int status) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	}
	public CustomExceptionHandleing(String message,String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	
	
}
