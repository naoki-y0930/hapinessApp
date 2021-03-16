package com.example.hapiness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HapinessHomeController {

	@GetMapping("/")
	public String getHome() {
		System.out.println("home画面");
		return "hapinessHome";
	}
}
