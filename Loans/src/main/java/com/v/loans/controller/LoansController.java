package com.v.loans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v.loans.LoansApplication;
import com.v.loans.dto.LoansContactInfoDto;
import com.v.loans.dto.LoansDto;
import com.v.loans.responsestructure.ErrorResponse;
import com.v.loans.responsestructure.ResponseStructure;
import com.v.loans.service.ILoansService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author V
 */

@Tag(name = "CRUD REST APIs for Loans in Bank", description = "CRUD REST APIs in Bank to CREATE, UPDATE, FETCH AND DELETE loan details")
@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class LoansController {

	private ILoansService iLoansService;

	public LoansController(ILoansService iLoansService) {
		super();
		this.iLoansService = iLoansService;
	}

	@Autowired
	private LoansContactInfoDto loansContactInfoDto;

	@Autowired
	private Environment environment;

//	@Value("${build.version}")
//	private String buildVersion;

	@Operation(summary = "Create Loan REST API", description = "REST API to create new loan inside Bank")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@PostMapping("create")
	public ResponseEntity<?> createLoan(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		ResponseStructure<?> loan = iLoansService.createLoan(mobileNumber);
		return ResponseEntity.status(loan.getStatusCode()).body(loan);
	}

	@GetMapping("fetch")
	public ResponseEntity<?> fetchLoan(@RequestParam String mobileNumber) {
		ResponseStructure<?> fetchLoan = iLoansService.fetchLoan(mobileNumber);
		return ResponseEntity.status(fetchLoan.getStatusCode()).body(fetchLoan);
	}

	@PutMapping("update")
	public ResponseEntity<?> updateLoan(@RequestBody LoansDto loansDto) {
		ResponseStructure<?> updateLoan = iLoansService.updateLoan(loansDto);
		return ResponseEntity.status(updateLoan.getStatusCode()).body(updateLoan);
	}

	@DeleteMapping("delete")
	public ResponseEntity<?> deleteLoan(@RequestParam String mobileNumber) {
		ResponseStructure<?> deleteLoan = iLoansService.deleteLoan(mobileNumber);
		return ResponseEntity.status(deleteLoan.getStatusCode()).body(deleteLoan);
	}

//	@Operation(summary = "Get Build information", description = "Get Build information that is deployed into accounts microservice")
//	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
//			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
//	@GetMapping("/build-info")
//	public ResponseEntity<String> getBuildInfo() {
//		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
//	}

	@Operation(summary = "Get Java version", description = "Get Java versions details that is installed into accounts microservice")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping("/java-version")
	public ResponseEntity<String> getJavaVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("accounts.contactDetails.name"));
	}

	@Operation(summary = "Get Contact Info", description = "Contact Info details that can be reached out in case of any issues")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping("/contact-info")
	public ResponseEntity<LoansContactInfoDto> getContactInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(loansContactInfoDto);
	}

}
