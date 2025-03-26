package com.v.accounts.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.v.accounts.responsestructure.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Handle generic exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse.ErrorDtoBuilder().setApiPath(request.getDescription(false))
				.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR).setErrorMessage(ex.getMessage())
				.setErroTime(LocalDateTime.now()).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//Handle Dto validations exception
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
//		Map<String,String> errors=new HashMap<>();
//		ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(), error.getDefaultMessage()));
		
		Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(k->k.getField(),v->v.getDefaultMessage()));
		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse.ErrorDtoBuilder().setApiPath(request.getDescription(false))
				.setErrorCode(HttpStatus.BAD_REQUEST).setErrorMessage(ex.getMessage()).setErroTime(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse.ErrorDtoBuilder().setApiPath(request.getDescription(false))
				.setErrorCode(HttpStatus.NOT_FOUND).setErrorMessage(ex.getMessage()).setErroTime(LocalDateTime.now())
				.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
