package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

	public CustomFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(CustomFilter.Config config) {
		// custom pre filter
		return (exchange, chain) -> {
			ServerHttpRequest httpRequest = exchange.getRequest();
			ServerHttpResponse httpResponse = exchange.getResponse();

			log.info("custom Pre filter httpRequest id : {}", httpRequest.getId());
			// custom post filter
			return chain.filter(exchange)
						.then(Mono.fromRunnable(
							() -> log.info("custom Post filter response Status Code : {}",
								httpResponse.getStatusCode())));
		};
	}

	public static class Config {
		// configuration properties 저장
	}
}
