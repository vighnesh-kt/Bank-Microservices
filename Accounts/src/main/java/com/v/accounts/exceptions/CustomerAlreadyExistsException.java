package com.v.accounts.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{
	
	public CustomerAlreadyExistsException(String messege) {
		super(messege);
	}

}
