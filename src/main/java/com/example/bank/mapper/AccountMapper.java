package com.example.bank.mapper;
import com.example.bank.dto.AccountDto;
import com.example.bank.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountDto accountDTO);
    @Mapping(target = "lang",ignore = true)
    AccountDto toDto(Account account);
    List<AccountDto> toDto(List<Account> accounts);
    List<Account> toEntity(List<AccountDto> accountDTO);
}
