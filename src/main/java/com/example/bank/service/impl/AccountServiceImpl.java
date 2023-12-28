package com.example.bank.service.impl;

import com.example.bank.dto.AccountDto;
import com.example.bank.entity.Account;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.repository.AccountRepository;
import com.example.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDto> findByStatus(String status, String lang) {
        List<AccountDto> accountDtoList = accountMapper.toDto(accountRepository.findByStatus(status));
        accountDtoList.forEach(accountDto -> {
            accountDto.setLang(lang);
        });
        return accountDtoList;
    }


    @Override
    public List<AccountDto> findByMinBalance(int minBalance) {
        List<AccountDto> accountDtoListMin = accountMapper.toDto(accountRepository.findByBalanceGreaterThanEqual(minBalance));
        accountDtoListMin.stream()
                .map(AccountDto::getStatus)
                .collect(Collectors.toList());
        return accountDtoListMin;
    }

    @Override
    public List<AccountDto> findByMaxBalance(int maxBalance) {
        List<AccountDto> accountDtoListMax = accountMapper.toDto(accountRepository.findByBalanceLessThanEqual(maxBalance));
        accountDtoListMax.stream()
                .map(AccountDto::getStatus)
                .collect(Collectors.toList());
        return accountDtoListMax;
    }

    @Override
    public List<AccountDto> findByOpenDate(String openDate) {
        List<AccountDto> accountDtoListOpenDate = accountMapper.toDto(accountRepository.findByOpenDate(openDate));
        accountDtoListOpenDate.stream()
                .map(AccountDto::getStatus)
                .collect(Collectors.toList());
        return accountDtoListOpenDate;
    }

    @Override
    public List<AccountDto> findByCloseDate(String closeDate) {
        List<AccountDto> accountDtoListCloseDate = accountMapper.toDto(accountRepository.findByCloseDate(closeDate));
        accountDtoListCloseDate.stream()
                .map(AccountDto::getStatus)
                .collect(Collectors.toList());
        return accountDtoListCloseDate;
    }

    @Override
    public List<AccountDto> findById(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);

        if (account != null) {
            return Collections.singletonList(accountMapper.toDto(account));
        } else {
            return Collections.emptyList();
        }
    }




    @Override
    public List<AccountDto> findByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
