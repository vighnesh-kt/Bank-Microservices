package com.v.accounts.service;


import com.v.accounts.dto.CustomerDto;
import com.v.accounts.responsestructure.ResponseStructure;

public interface IAccountsService {
	

	/**
	 * \
	 * @param customer
	 * @return
	 */
	ResponseStructure<?> create(CustomerDto customer);
	
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	ResponseStructure<?> fetch(String mobileNumber);


}
