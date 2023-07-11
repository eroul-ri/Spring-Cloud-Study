package com.example.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class FirstServiceController {

	private Environment env;

	public FirstServiceController(Environment env) {
		this.env = env;
	}

	@GetMapping("/first-service/welcome")
	public String welcome() {
		return "Hello World, Welcome First Service";
	}

	@GetMapping("/first-service/message")
	public String message(@RequestHeader("first-request") String header) {
		log.info("first-service/message header :: {}", header);
		return "Hello World in First Service";
	}

	@GetMapping("/first-service/check")
	public String check(HttpServletRequest request) {
		log.info("Request Server Port :{}", request.getServerPort());
		return String.format("Hi There, This is a message from First Service Port : %s",
			env.getProperty("local.server.port"));
	}
}
