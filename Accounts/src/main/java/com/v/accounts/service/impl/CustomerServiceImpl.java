package com.v.accounts.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v.accounts.dto.AccountsDto;
import com.v.accounts.dto.CardsDto;
import com.v.accounts.dto.CustomerDetailsDto;
import com.v.accounts.dto.LoansDto;
import com.v.accounts.entity.Accounts;
import com.v.accounts.entity.Customer;
import com.v.accounts.exceptions.ResourceNotFoundException;
import com.v.accounts.mapper.AccountsMapper;
import com.v.accounts.mapper.CustomerMapperStruct;
import com.v.accounts.repository.AccountsRepository;
import com.v.accounts.repository.CustomerRepository;
import com.v.accounts.responsestructure.ResponseStructure;
import com.v.accounts.service.ICustomerService;
import com.v.accounts.service.client.CardsFeignClient;
import com.v.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomerService {

	private AccountsRepository accountsRepository;

	private CustomerRepository customerRepository;

	private CardsFeignClient cardsFeignClient;

	private LoansFeignClient loansFeignClient;

	private CustomerMapperStruct customerMapperStruct;

	@Override
	public ResponseStructure<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber) {
		// TODO Auto-generated method stub
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

		CustomerDetailsDto customerDetailsDto = customerMapperStruct.toCustomerDetailsDto(customer);
		customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

		// leverate feign client to get loans and accounts details

		ResponseStructure<LoansDto> fetchLoan = loansFeignClient.fetchLoan(mobileNumber);

		ResponseEntity<CardsDto> fetchCardDetails = cardsFeignClient.fetchCardDetails(mobileNumber);

		customerDetailsDto.setLoansDto(fetchLoan!=null?fetchLoan.getData():null);
		customerDetailsDto.setCardsDto(fetchCardDetails!=null?fetchCardDetails.getBody():null);

		return ResponseStructure.<CustomerDetailsDto>builder().data(customerDetailsDto).message("Details Fetched")
				.statusCode(HttpStatus.OK.value()).build();
	}

}
