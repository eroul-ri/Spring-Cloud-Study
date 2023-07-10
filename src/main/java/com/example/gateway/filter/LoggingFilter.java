package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
	public LoggingFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		/*
		return ((exchange, chain) -> {
			log.info(" Logging Filter Base Message : {}", config.getBaseMessage());

			ServerHttpRequest httpRequest = exchange.getRequest();
			ServerHttpResponse httpResponse = exchange.getResponse();

			if(config.isPreLogger()) {
				log.info(" Logging Filter Pre log : request Uri -> {} ", httpRequest.getURI());
			}

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				if(config.isPostLogger()) {
					log.info(" Logging Filter Post log : response StatusCode -> {} ", httpResponse.getStatusCode());
				}
			}));
		});
		 */
		GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
			log.info(" Logging Filter Base Message : {}", config.getBaseMessage());

			ServerHttpRequest httpRequest = exchange.getRequest();
			ServerHttpResponse httpResponse = exchange.getResponse();

			if(config.isPreLogger()) {
				log.info(" Logging Filter Pre log : request Uri -> {} ", httpRequest.getURI());
			}

			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				if(config.isPostLogger()) {
					log.info(" Logging Filter Post log : response StatusCode -> {} ", httpResponse.getStatusCode());
				}
			}));
		},
			Ordered.HIGHEST_PRECEDENCE); // HIGHEST_PRECEDENCE - 실행순서 우선순위를 가장 높게 지정, LOWEST_PRECEDENCE - 실행순서 우선순위를 가장 낮게 지정

		return filter;
	}

	@Data
	public static class Config {
		private String baseMessage;

		private boolean preLogger;
		private boolean postLogger;
	}
}
