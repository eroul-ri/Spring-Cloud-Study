package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {
	public GlobalFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			log.info(" Global Filter Base Message : {}", config.getBaseMessage());

			ServerHttpRequest httpRequest = exchange.getRequest();
			ServerHttpResponse httpResponse = exchange.getResponse();

			if(config.isPreLogger()) {
				log.info(" Global Filter Pre log : reuqest Id -> {} ", httpRequest.getId());
			}
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				if(config.isPostLogger()) {
					log.info(" Global Filter Post log : StatusCode -> {} ", httpResponse.getStatusCode());
				}
			}));
		});
	}

	@Data
	public static class Config {
		private String baseMessage;

		private boolean preLogger;
		private boolean postLogger;
	}
}
