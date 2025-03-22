package com.v.accounts.mapper;

import org.mapstruct.Mapper;

import com.v.accounts.dto.AccountsDto;
import com.v.accounts.entity.Accounts;


@Mapper(componentModel = "spring")
public interface AccountsMapperStruct {
	
    AccountsDto toDto(Accounts entity);
    
    Accounts toEntity(AccountsDto dto);
}
