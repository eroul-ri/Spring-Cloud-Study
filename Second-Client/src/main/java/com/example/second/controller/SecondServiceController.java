package com.example.second.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondServiceController {

	@GetMapping("/second-service/welcome")
	public String welcome() {
		return "Hello World, Welcome Second Service";
	}
}
