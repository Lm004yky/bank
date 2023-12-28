package com.example.bank.service;

import com.example.bank.dto.BankDto;
import com.example.bank.entity.Bank;

import java.util.List;

public interface BankService {
    List<BankDto> findByName(String name);
    BankDto updateBank(Long id, BankDto bankDto);
    void deleteBankById(Long id);
    BankDto createBank(BankDto bankDto);
}

