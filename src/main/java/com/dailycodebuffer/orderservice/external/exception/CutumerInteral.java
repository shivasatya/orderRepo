package com.dailycodebuffer.orderservice.external.exception;

import java.util.Collection;
import java.util.Map;

import feign.FeignException.InternalServerError;
import feign.Request;
import lombok.Data;

@Data
public class CutumerInteral extends InternalServerError  {

	private String request; 
	private String message;
	
	public CutumerInteral(String message, Request request, byte[] body, Map<String, Collection<String>> headers, Object object) {
		super(message, request, body, headers);
		this.request = String.valueOf(request.body().toString());
	}

	
}
