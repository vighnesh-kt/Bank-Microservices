package com.v.loans.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

/**
 * @author V
 */

@Tag(
        name = "CRUD REST APIs for Loans in Bank",
        description = "CRUD REST APIs in Bank to CREATE, UPDATE, FETCH AND DELETE loan details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {
	
	 private ILoansService iLoansService;

	    @Operation(
	            summary = "Create Loan REST API",
	            description = "REST API to create new loan inside Bank"
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
	    @PostMapping("/create")
	    public ResponseEntity<?> createLoan(@RequestParam
	                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                      String mobileNumber) {
	        ResponseStructure<?> loan = iLoansService.createLoan(mobileNumber);
	        return ResponseEntity.status(loan.getStatusCode()).body(loan);
	    }

}
