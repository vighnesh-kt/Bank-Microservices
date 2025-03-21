package com.v.accounts.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
	
	@GetMapping("home")
	public String getMethodName() {
		return "Hello world";
	}
	

}
