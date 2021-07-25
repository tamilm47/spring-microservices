package com.example.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	private static int count = 0;
	
	@GetMapping("/sample-api")
//	@Retry(name = "sample-api", fallbackMethod = "fallBackResponse")
//	@CircuitBreaker(name = "sample-api", fallbackMethod = "fallBackResponse")
//	@RateLimiter(name = "sample-api", fallbackMethod = "fallBackResponse")
	@Bulkhead(name = "sample-api")
	public String sampleApi() {
		
//		logger.info("Retrying...." + String.valueOf(count++));
//		logger.info("Circuit Breaker...." + String.valueOf(count++));
		logger.info("Ratelimiter...." + String.valueOf(count++));
		
		ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8200/url", String.class);
		return response.getBody();
	}
	
	private String fallBackResponse(Exception e) {
		return "DEFAULT FALLBACK RESPONSE";
	}
}
