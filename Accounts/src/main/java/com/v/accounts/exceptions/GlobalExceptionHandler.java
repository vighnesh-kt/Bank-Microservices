package com.v.accounts.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.v.accounts.responsestructure.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex,WebRequest request) {
		ErrorResponse errorResponse=new ErrorResponse.ErrorDtoBuilder()
				.setApiPath(request.getDescription(false))
				.setErrorCode(HttpStatus.BAD_REQUEST)
				.setErrorMessage(ex.getMessage())
				.setErroTime(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest request){
		ErrorResponse errorResponse =new ErrorResponse.ErrorDtoBuilder()
				.setApiPath(request.getDescription(false))
				.setErrorCode(HttpStatus.NOT_FOUND)
				.setErrorMessage(ex.getMessage())
				.setErroTime(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
}
