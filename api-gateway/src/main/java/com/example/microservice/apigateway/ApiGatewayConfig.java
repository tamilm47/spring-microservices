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
		Function<PredicateSpec, Buildable<Route>> sample = p->p.path("/get")
				.filters(f->f.addRequestHeader("custom-header", "header1")
				.addRequestParameter("custom-param", "param1")).uri("http://httpbin.org:80"); 
		Function<PredicateSpec, Buildable<Route>> currencyExchange = p->p.path("/currency-exchange/**").uri("lb://currency-exchange");
		return builder.routes().route(sample).route(currencyExchange).build();
	}
}
