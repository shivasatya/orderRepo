package com.dailycodebuffer.orderservice.external.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dailycodebuffer.orderservice.external.response.ErrorResponse;



@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CustomExceptionHandleing.class)
	public ResponseEntity<ErrorResponse> handelProductCustomException(CustomExceptionHandleing exception) {
		return new ResponseEntity<>(ErrorResponse.builder()
				.errorMessage(exception.getMessage())
				.errorCode(exception.getErrorCode())
				.build(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(CutumerInteral.class)
	public ResponseEntity<ErrorResponse> handelProductCustomExceptions(CutumerInteral exception) {
		return new ResponseEntity<>(ErrorResponse.builder()
				.errorMessage(exception.getMessage())
				.errorCode(exception.getRequest())
				.build(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
