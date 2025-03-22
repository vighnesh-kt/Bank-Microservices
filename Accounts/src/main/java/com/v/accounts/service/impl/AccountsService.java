package com.v.accounts.service.impl;


import java.util.Optional;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v.accounts.dto.CustomerDto;
import com.v.accounts.entity.Accounts;
import com.v.accounts.entity.Customer;
import com.v.accounts.enums.AccountsConstants;
import com.v.accounts.exceptions.CustomerAlreadyExistsException;
import com.v.accounts.mapper.AccountsMapperStruct;
import com.v.accounts.mapper.CustomerMapperStruct;
import com.v.accounts.repository.AccountsRepository;
import com.v.accounts.repository.CustomerRepository;
import com.v.accounts.responsestructure.ResponseStructure;
import com.v.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;

/**
 * If there is only one constructor, Spring automatically performs dependency injection, and @Autowired is not required.
 */
@Service
@AllArgsConstructor
public class AccountsService implements IAccountsService{
	
	private AccountsRepository accountRepository;
	
	private CustomerRepository customerRepository;
	
	private CustomerMapperStruct customerMapper;
	
	private AccountsMapperStruct accountMapper;

	@Override
	public ResponseEntity<?> create(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		if (customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent()) {
		    throw new CustomerAlreadyExistsException("Customer with this mobile number already exists");
		}
		ResponseStructure<String> rs= new ResponseStructure<>();
		Customer customer=customerMapper.toEntity(customerDto);
		accountRepository.save(createNewAccount(customer));
	return null;
		
		
	}
	
	  private Accounts createNewAccount(Customer customer) {
	        Accounts newAccount = new Accounts();
	        newAccount.setCustomerId(customer.getCustomerId());
	        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

	        newAccount.setAccountNumber(randomAccNumber);
	        newAccount.setAccountType(AccountsConstants.SAVINGS);
	        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
	        return newAccount;
	    }

}
