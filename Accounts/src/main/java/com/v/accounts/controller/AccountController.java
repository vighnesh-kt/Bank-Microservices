package com.v.accounts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.v.accounts.dto.CustomerDto;
import com.v.accounts.responsestructure.ResponseStructure;
import com.v.accounts.service.IAccountsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountController {
	
	private IAccountsService accountsService;

	@GetMapping("home")
	public String getMethodName() {
		return "Hello world";
	}

	@PostMapping("create")
	public ResponseEntity<?> create(@Valid @RequestBody CustomerDto customerDto) {
		ResponseStructure<?> create = accountsService.create(customerDto);
		return ResponseEntity.status(create.getStatusCode()).body(create);
	}
	
	@GetMapping("fetch")
	public ResponseEntity<?> fetchAccountDetails(
			@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")

			@RequestParam String mobileNumber) {
		ResponseStructure<?> fetch = accountsService.fetch(mobileNumber);
		return ResponseEntity.status(fetch.getStatusCode()).body(fetch);
	}
	
	@PatchMapping("update")
	public ResponseEntity<?> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		ResponseStructure<?> update = accountsService.update(customerDto);
		return ResponseEntity.status(update.getStatusCode()).body(update);
	}
	
	@DeleteMapping("delete")
	public ResponseEntity<?> deleteAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		ResponseStructure<?> delete = accountsService.delete(customerDto);
		return ResponseEntity.status(delete.getStatusCode()).body(delete);
	}

	
}
