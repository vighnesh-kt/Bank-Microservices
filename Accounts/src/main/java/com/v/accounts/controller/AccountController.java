package com.v.accounts.controller;

import org.springframework.web.bind.annotation.RestController;

import com.v.accounts.dto.CustomerDto;
import com.v.accounts.responsestructure.ErrorResponse;
import com.v.accounts.responsestructure.ResponseStructure;
import com.v.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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
@Tag(
        name = "CRUD REST APIs for Accounts in Bank",
        description = "CRUD REST APIs in Bank to CREATE, UPDATE, FETCH AND DELETE account details"
)
public class AccountController {
	
	private IAccountsService accountsService;

	@GetMapping("home")
	public String getMethodName() {
		return "Hello world";
	}

	@Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    }
    )
	@PostMapping("create")
	public ResponseEntity<?> create(@Valid @RequestBody CustomerDto customerDto) {
		ResponseStructure<?> create = accountsService.create(customerDto);
		return ResponseEntity.status(create.getStatusCode()).body(create);
	}
	
	
	 @Operation(
	            summary = "Fetch Account Details REST API",
	            description = "REST API to fetch Customer &  Account details based on a mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponse.class)
	                    )
	            )
	    }
	    )
	@GetMapping("fetch")
	public ResponseEntity<?> fetchAccountDetails(
			@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")

			@RequestParam String mobileNumber) {
		ResponseStructure<?> fetch = accountsService.fetch(mobileNumber);
		return ResponseEntity.status(fetch.getStatusCode()).body(fetch);
	}
	
	 
	 @Operation(
	            summary = "Update Account Details REST API",
	            description = "REST API to update Customer &  Account details based on a account number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "417",
	                    description = "Expectation Failed"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponse.class)
	                    )
	            )
	    }
	    )
	@PatchMapping("update")
	public ResponseEntity<?> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		ResponseStructure<?> update = accountsService.update(customerDto);
		return ResponseEntity.status(update.getStatusCode()).body(update);
	}
	
	 @Operation(
	            summary = "Delete Account & Customer Details REST API",
	            description = "REST API to delete Customer &  Account details based on a mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "417",
	                    description = "Expectation Failed"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponse.class)
	                    )
	            )
	    }
	    )
	@DeleteMapping("delete")
	public ResponseEntity<?> deleteAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		ResponseStructure<?> delete = accountsService.delete(customerDto);
		return ResponseEntity.status(delete.getStatusCode()).body(delete);
	}

	
}
