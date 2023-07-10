package com.example.second.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SecondServiceController {

	@GetMapping("/second-service/welcome")
	public String welcome() {
		return "Hello World, Welcome Second Service";
	}

	@GetMapping("/second-service/message")
	public String message(@RequestHeader("second-request") String header) {
		log.info("second-service/message header :: {}", header);
		return "Hello World in Second Service";
	}

	@GetMapping("/second-service/check")
	public String check() {
		return "Hi There, This is a message from Second Service";
	}
}
