package com.dailycodebuffer.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dailycodebuffer.orderservice.external.decoder.Decoder;
import com.dailycodebuffer.orderservice.external.exception.CustomExceptionHandleing;

import feign.codec.ErrorDecoder;

@Configuration
public class FeigConfig {
	
	@Bean
	ErrorDecoder errorDecoder() {
		return new Decoder();
	}

}
