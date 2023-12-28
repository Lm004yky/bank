package com.example.bank.mapper;

import com.example.bank.dto.BankDto;
import com.example.bank.entity.Bank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper {
    Bank toEntity(BankDto bankDTO);
    BankDto toDto(Bank bank);
}
