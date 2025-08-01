package com.v.accounts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v.accounts.dto.CustomerDetailsDto;
import com.v.accounts.responsestructure.ErrorResponse;
import com.v.accounts.responsestructure.ResponseStructure;
import com.v.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(
        name = "REST APIs for Customers in Bank",
        description = "REST APIs in Bank to fetch customer details"
)
@AllArgsConstructor
public class CustomerController {
	
	private ICustomerService customerService;
	
	private static final Logger logger= LoggerFactory.getLogger(AccountController.class);

	
	 @Operation(
	            summary = "Fetch Customer Details REST API",
	            description = "REST API to fetch Customer details based on a mobile number"
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
	@GetMapping("fetchCustomerDetails")
	public ResponseEntity<?> customerDetails(
			@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
			@RequestParam String mobileNumber){
		 
		logger.info("customerDetails method start");
		 ResponseStructure<?> fetchCustomerDetails = customerService.fetchCustomerDetails(mobileNumber);
		 logger.info("customerDetails method end");
		 return ResponseEntity.status(HttpStatus.OK).body(fetchCustomerDetails);
	}

}
