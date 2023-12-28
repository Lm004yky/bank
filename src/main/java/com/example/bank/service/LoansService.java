package com.example.bank.service;

import com.example.bank.dto.LoansDto;
import com.example.bank.entity.Loans;

import java.util.List;

public interface LoansService {
    List<LoansDto> findByLoanStatus(String loanStatus);
    List<LoansDto> findByMinAmount(int minLoanAmount);
    List<LoansDto> findByMaxAmount(int maxLoanAmount);
    LoansDto create(LoansDto loanDto);
    LoansDto update(Long id, LoansDto loanDto);
    void deleteById(Long id);
}

