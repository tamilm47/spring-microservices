package com.example.microservice.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

	@Bean
	public RouteLocator apiGatewayRoute(RouteLocatorBuilder builder) {
		// sample get request
		Function<PredicateSpec, Buildable<Route>> sample = p->p.path("/get")
				.filters(f->f.addRequestHeader("custom-header", "header1")
				.addRequestParameter("custom-param", "param1")).uri("http://httpbin.org:80");
		// currency-exchange
		Function<PredicateSpec, Buildable<Route>> currencyExchange = p->p.path("/currency-exchange/**").uri("lb://currency-exchange");
		// currency-conversion
		Function<PredicateSpec, Buildable<Route>> currencyConversion = p->p.path("/currency-conversion/**").uri("lb://currency-conversion");
		// currency-conversion-feign
		Function<PredicateSpec, Buildable<Route>> currencyConversionFeign = p->p.path("currency-conversion-feign/**").uri("lb://currency-conversion");
		// currency-conversion-new -> currency-conversion-feign and rewrite path sample
		Function<PredicateSpec, Buildable<Route>> currencyConversionRewrite = p->p.path("/currency-conversion-new/**")
				.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)","/currency-conversion-feign/${segment}"))
				.uri("lb://currency-conversion");
		
		return builder.routes().
				route(sample).
				route(currencyExchange).
				route(currencyConversion).
				route(currencyConversionFeign).
				route(currencyConversionRewrite).build();
	}
}
