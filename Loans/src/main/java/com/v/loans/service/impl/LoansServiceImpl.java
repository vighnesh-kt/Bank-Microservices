package com.v.loans.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.v.loans.constants.LoansConstants;
import com.v.loans.dto.LoansDto;
import com.v.loans.entity.Loans;
import com.v.loans.exceptions.LoanAlreadyExistsException;
import com.v.loans.exceptions.ResourceNotFoundException;
import com.v.loans.mapper.LoansMapper;
import com.v.loans.responsestructure.ResponseStructure;
import com.v.loans.respository.LoansRepository;
import com.v.loans.service.ILoansService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
	
	private  LoansRepository loansRepository;
	
	private  LoansMapper loansMapper;

	@Override
	public ResponseStructure<?> createLoan(String mobileNumber) {
		loansRepository.findByMobileNumber(mobileNumber).ifPresent(loan->{
			throw new LoanAlreadyExistsException("Loan already exist");
			});
		Loans loan = createNewLoan(mobileNumber);
		loansRepository.save(loan);
		return ResponseStructure.<String>builder().data(loan.getLoanNumber()).message(LoansConstants.MESSAGE_201)
				.statusCode(HttpStatus.CREATED.value()).build();
	}
	
    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }


    /**
    *
    * @param mobileNumber - Input mobile Number
    * @return Loan Details based on a given mobileNumber
    */
	@Override
	public ResponseStructure<?> fetchLoan(String mobileNumber) {
		// TODO Auto-generated method stub
		  Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
	               () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
	       );
	       LoansDto dto = loansMapper.toDto(loans);
	       return ResponseStructure.<LoansDto>builder().data(dto).message(LoansConstants.MESSAGE_200)
					.statusCode(HttpStatus.OK.value()).build();
	}

	@Override
	public ResponseStructure<?> updateLoan(LoansDto loansDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseStructure<?> deleteLoan(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
