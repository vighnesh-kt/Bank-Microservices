package com.v.accounts.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.v.accounts.dto.AccountsDto;
import com.v.accounts.entity.Accounts;


@Mapper(componentModel = "spring")
public interface AccountsMapperStruct {
	
    AccountsDto toDto(Accounts entity);
    
    Accounts toEntity(AccountsDto dto);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(AccountsDto accountDto,@MappingTarget Accounts account);
}
