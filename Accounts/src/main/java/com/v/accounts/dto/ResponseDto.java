package com.v.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
	
	String statusCode;
	
	private String statusMsg;

}
