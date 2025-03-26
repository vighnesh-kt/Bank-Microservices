package com.v.loans.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.v.loans.dto.LoansDto;
import com.v.loans.entity.Loans;

@Mapper(componentModel = "spring")
public interface LoansMapper {
	
	LoansDto toDto(Loans loan);
	
	Loans toEntity (LoansDto loanDto);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntity(LoansDto loanDto,@MappingTarget Loans loan);
	

}
