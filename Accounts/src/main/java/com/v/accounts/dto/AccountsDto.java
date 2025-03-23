package com.v.accounts.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
	

	    private Long customerId;

	    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile no must be 10 digit")
	    @NotEmpty(message = "accountNumber cannot be null") 
	    private Long accountNumber;

	    @NotEmpty(message = "accountType cannot be null") 
	    private String accountType;

	    @NotEmpty(message = "branchAddress cannot be null") 
	    private String branchAddress;
	   

}
