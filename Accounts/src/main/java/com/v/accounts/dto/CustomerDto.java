package com.v.accounts.dto; 

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
	
	@NotEmpty(message = "Name cannot be null") 
	// when validation fail due to notempty messege to be shown in exception which failed
	// The annotated element must not be null nor empty.
	@Size(min = 3,max = 30,message = "Size must be min 5 and max 30")
	private String name;


	@NotEmpty(message = "mobileNumber cannot be null")
	@Email(message = "Email should be valid") //automatically handle email pattern
	private String email;


	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile no must be 10 digit")
	private String mobileNumber;
	
	private AccountsDto accounts;

}
