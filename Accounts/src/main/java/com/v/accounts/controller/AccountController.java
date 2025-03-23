package com.v.accounts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.v.accounts.dto.CustomerDto;
import com.v.accounts.service.IAccountsService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountController {
	
	private IAccountsService accountsService;

	@GetMapping("home")
	public String getMethodName() {
		return "Hello world";
	}

	@PostMapping("create")
	public ResponseEntity<?> create(@Valid @RequestBody CustomerDto customer) {
		return ResponseEntity.status(HttpStatus.CREATED).body(accountsService.create(customer));
	}
	
	@GetMapping("fetch")
	public ResponseEntity<?> fetchAccountDetails(@RequestParam String mobileNumber) {
		return ResponseEntity.status(HttpStatus.OK).body(accountsService.fetch(mobileNumber));
	}
	

	
}
