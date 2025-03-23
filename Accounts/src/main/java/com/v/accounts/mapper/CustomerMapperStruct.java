package com.v.accounts.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.v.accounts.dto.CustomerDto;
import com.v.accounts.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapperStruct {
	CustomerDto toDto(Customer customer);

	Customer toEntity(CustomerDto customerDto);

	// Update an existing Customer entity with only non-null values from DTO
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntity(CustomerDto customerDto, @MappingTarget Customer customer);
}
