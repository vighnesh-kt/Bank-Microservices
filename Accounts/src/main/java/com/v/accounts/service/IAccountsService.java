package com.v.accounts.service;

import org.springframework.http.ResponseEntity;

import com.v.accounts.dto.CustomerDto;

public interface IAccountsService {
	

	/**
	 * \
	 * @param customer
	 * @return
	 */
	ResponseEntity<?> create(CustomerDto customer);


}
