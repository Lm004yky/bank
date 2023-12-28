package com.example.bank.service;

import com.example.bank.dto.AccountDto;
import com.example.bank.entity.Account;

import java.util.List;

public interface AccountService {
    List<AccountDto> findByStatus(String status,String lang);

    List<AccountDto> findByMinBalance(int minBalance);

    List<AccountDto> findByMaxBalance(int maxBalance);

    List<AccountDto> findByOpenDate(String openDate);

    List<AccountDto> findByCloseDate(String closeDate);

    List<AccountDto> findByPhoneNumber(String phoneNumber);
    List<AccountDto> findById(Long id);
    Account create(Account account);

    void deleteById(Long id);


}
