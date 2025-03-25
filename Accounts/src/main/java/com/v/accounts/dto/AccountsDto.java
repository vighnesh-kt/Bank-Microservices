package com.v.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold Account information")
public class AccountsDto {
	

	    private Long customerId;

	    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile no must be 10 digit")
	    @NotEmpty(message = "accountNumber cannot be null") 
	    @Schema(
	            description = "Account Number of Bank account", example = "3454433243"
	    )
	    private Long accountNumber;
	

	    @NotEmpty(message = "AccountType can not be a null or empty")
	    @Schema(
	            description = "Account type of Eazy Bank account", example = "Savings"
	    )
	    private String accountType;

	    @NotEmpty(message = "BranchAddress can not be a null or empty")
	    @Schema(
	            description = "Bank branch address", example = "123 NewYork"
	    )
	    private String branchAddress;
	   

}
