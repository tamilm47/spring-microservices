package com.example.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	CurrencyExchangeProxy currencyExchangeProxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getValue(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {
		
		HashMap<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> currencyConversionValue = 
				new RestTemplate().getForEntity("http://localhost:8200/currency-exchange/from/{from}/to/{to}", 
						CurrencyConversion.class, uriVariables);
		
		CurrencyConversion conversion = currencyConversionValue.getBody();
		conversion.setConvertedValue(quantity.multiply(conversion.getConversionRate()));
		return conversion;
		
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getValueFeign(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("quantity") BigDecimal quantity) {
		
//		HashMap<String,String> uriVariables = new HashMap<>();
//		uriVariables.put("from", from);
//		uriVariables.put("to", to);
//		ResponseEntity<CurrencyConversion> currencyConversionValue = 
//				new RestTemplate().getForEntity("http://localhost:8200/currency-exchange/from/{from}/to/{to}", 
//						CurrencyConversion.class, uriVariables);
		
//		CurrencyConversion conversion = currencyConversionValue.getBody();
		
		CurrencyConversion conversion = currencyExchangeProxy.getValue(from, to);
		conversion.setConvertedValue(quantity.multiply(conversion.getConversionRate()));
		return conversion;
		
	}
}
