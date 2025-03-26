package com.v.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Account information")
public class CustomerDto {

	@Schema(description = "Account Number of Bank account", example = "3454433243")
	@NotEmpty(message = "Name cannot be null")
	// when validation fail due to notempty messege to be shown in exception which
	// failed
	// The annotated element must not be null nor empty.
	@Size(min = 3, max = 30, message = "Size must be min 5 and max 30")
	private String name;
	@Schema(description = "Email address of the customer", example = "tutor.com")
	@NotEmpty(message = "Email address can not be a null or empty")
	@Email(message = "Email address should be a valid value")
	private String email;

	@Schema(description = "Mobile Number of the customer", example = "9345432123")
	@Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile no must be 10 digit")
	private String mobileNumber;

	@Schema(description = "Account details of the Customer")
	private AccountsDto accounts;

}
