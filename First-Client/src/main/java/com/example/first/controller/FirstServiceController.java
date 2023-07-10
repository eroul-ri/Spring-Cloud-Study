package com.example.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstServiceController {

	@GetMapping("/welcome")
	public String welcome() {
		return "Hello World, Welcome First Service";
	}
}
