package com.v.accounts.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AccountsDto {
	

	    private Long customerId;

	    
	    private Long accountNumber;

	  
	    private String accountType;

	    private String branchAddress;
	   

}
