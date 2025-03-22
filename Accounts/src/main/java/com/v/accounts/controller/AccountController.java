package com.v.accounts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.v.accounts.dto.CustomerDto;
import com.v.accounts.responsestructure.ResponseStructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

	@GetMapping("home")
	public String getMethodName() {
		return "Hello world";
	}

	@PostMapping("create")
	public ResponseEntity<?> create(@RequestBody CustomerDto customer) {
		return null;
		
	}

	
}
