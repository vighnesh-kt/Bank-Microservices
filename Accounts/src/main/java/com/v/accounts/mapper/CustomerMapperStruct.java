package com.v.accounts.mapper;

import org.mapstruct.Mapper;

import com.v.accounts.dto.CustomerDto;
import com.v.accounts.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapperStruct {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
}
