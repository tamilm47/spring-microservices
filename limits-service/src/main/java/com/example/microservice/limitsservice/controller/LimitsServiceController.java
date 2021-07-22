package com.example.microservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.limitsservice.bean.Limits;
import com.example.microservice.limitsservice.config.LimitsServiceConfig;

@RestController
public class LimitsServiceController {
	
	@Autowired
	private LimitsServiceConfig config;
	
	@GetMapping("/getLimits")
	public Limits getLimits() {
//		return new Limits(1, 1000);
		return new Limits(config.getMinimum(), config.getMaximum());
	}
}
