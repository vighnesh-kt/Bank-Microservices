package com.v.accounts.responsestructure;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {
	
	private T data;
	
	private String message;
	

	private int statusCode; 
	
	public ResponseStructure<T> successResponse(T data,String message,int statusCode){
		this.data=data;
		this.message=message;
		this.statusCode=statusCode;
		return this;
	}
	
	public ResponseStructure<T> errorResponse(String message,int statusCode){
		this.message=message;
		this.statusCode=statusCode;
		this.data=null;
		return this;
	}

}
