package com.v.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import com.v.accounts.dto.AccountsDto;
import com.v.accounts.dto.AccountsMsgDto;
import com.v.accounts.dto.CustomerDto;
import com.v.accounts.entity.Accounts;
import com.v.accounts.entity.Customer;
import com.v.accounts.enums.AccountsConstants;
import com.v.accounts.exceptions.CustomerAlreadyExistsException;
import com.v.accounts.exceptions.ResourceNotFoundException;
import com.v.accounts.mapper.AccountsMapperStruct;
import com.v.accounts.mapper.CustomerMapperStruct;
import com.v.accounts.repository.AccountsRepository;
import com.v.accounts.repository.CustomerRepository;
import com.v.accounts.responsestructure.ResponseStructure;
import com.v.accounts.service.IAccountsService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

/**
 * If there is only one constructor, Spring automatically performs dependency
 * injection, and @Autowired is not required.
 */
@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

	private static final Logger log = LoggerFactory.getLogger(AccountsServiceImpl.class);

	public final StreamBridge streamBridge;

	private AccountsRepository accountRepository;

	private CustomerRepository customerRepository;

	private CustomerMapperStruct customerMapper;

	private AccountsMapperStruct accountMapper;

	@Override
	public ResponseStructure<String> create(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		customerRepository.findByMobileNumber(customerDto.getMobileNumber()).ifPresent(customer -> {
			throw new CustomerAlreadyExistsException("Customer Already Exists");
		});
		Customer customer = customerMapper.toEntity(customerDto);
//		customer.setCreatedAt(LocalDateTime.now());
//		customer.setCreatedBy("Annanimus");
		customerRepository.save(customer);
		Accounts save = accountRepository.save(createNewAccount(customer));
		sendCommunication(save, customer);

		return ResponseStructure.<String>builder().data(customer.getEmail()).message(AccountsConstants.MESSAGE_201)
				.statusCode(HttpStatus.CREATED.value()).build();
	}

	private void sendCommunication(Accounts account, Customer customer) {
		var accountsMsgDto = new AccountsMsgDto(account.getAccountNumber(), customer.getName(), customer.getEmail(),
				customer.getMobileNumber());
		log.info("Sending Communication request for the details: {}", accountsMsgDto);
		var result = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
		log.info("Is the Communication request successfully triggered ? : {}", result);
	}

	/**
	 * Creating account for customer
	 * 
	 * @param customer
	 * @return
	 */
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.ADDRESS);
		return newAccount;
	}

	@Override
	public ResponseStructure<?> fetch(String mobileNumber) {
		// TODO Auto-generated method stub

		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

		CustomerDto customerDto = customerMapper.toCustomerDto(customer);
		customerDto.setAccountsDto(accountMapper.toDto(account));
		return ResponseStructure.<CustomerDto>builder().data(customerDto).message("Details Fetched")
				.statusCode(HttpStatus.OK.value()).build();
	}

	@Override
	@Transactional
	public ResponseStructure<?> update(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (accountsDto.getAccountNumber() != null) {
			Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber",
							accountsDto.getAccountNumber().toString()));

			accountMapper.updateEntity(accountsDto, accounts);
			accountRepository.save(accounts);

			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));

			customerMapper.updateEntity(customerDto, customer);
			customerRepository.save(customer);
			isUpdated = true;
		}
		if (isUpdated) {
			return ResponseStructure.<String>builder().statusCode(HttpStatus.OK.value())
					.message("Customer Updated Successfully").data(customerDto.getName()).build();
		}
		return ResponseStructure.<String>builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("Customer Updated Failed").data(customerDto.getName()).build();
	}

	@Override
	@Transactional
	public ResponseStructure<?> delete(String mobileNumber) {

		boolean isDeleted = false;
		if (mobileNumber != null) {
			Customer customer = customerRepository.findByMobileNumber(mobileNumber)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

			accountRepository.deleteByCustomerId(customer.getCustomerId());
			customerRepository.deleteById(customer.getCustomerId());
			isDeleted = true;
		}
		if (isDeleted) {
			return ResponseStructure.<String>builder().statusCode(HttpStatus.OK.value())
					.message("Customer Deleted Successfully").data(mobileNumber).build();
		}
		return ResponseStructure.<String>builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("Customer Deleted Failed").data(mobileNumber).build();

	}

	@Override
	public boolean updateCommunicationStatus(Long accountNumber) {
		// TODO Auto-generated method stub
		boolean isUpdated=false;
		if(accountNumber!=null) {
			Accounts account = accountRepository.findById(accountNumber).orElseThrow(
					()->new ResourceNotFoundException("Account", "AccountNumber", accountNumber.toString())
		);
		account.setCommunicationSwitch(true);
		accountRepository.save(account);
		isUpdated=true;
		}
		return isUpdated;
	}
}
