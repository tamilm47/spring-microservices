package com.example.microservice.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversion {

	private long id;
	
	private String from;
	
	private String to;
	
	private BigDecimal quantity;
	
	private BigDecimal conversionRate;
	
	private BigDecimal convertedValue;
	
	private String environment;
	
	public CurrencyConversion() {
		
	}
	
	public CurrencyConversion(long id, String from, String to, BigDecimal quantity, BigDecimal conversionRate, 
			BigDecimal convertedValue, String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.conversionRate = conversionRate;
		this.convertedValue = convertedValue;
		this.environment = environment;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public BigDecimal getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(BigDecimal convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	
}
