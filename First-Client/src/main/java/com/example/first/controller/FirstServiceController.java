package com.example.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class FirstServiceController {

	@GetMapping("/first-service/welcome")
	public String welcome() {
		return "Hello World, Welcome First Service";
	}

	@GetMapping("/first-service/message")
	public String message(@RequestHeader("first-request") String header) {
		log.info("first-service/message header :: {}", header);
		return "Hello World in First Service";
	}
}
