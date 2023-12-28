package com.example.bank.service.impl;

import com.example.bank.dto.BankDto;
import com.example.bank.entity.Bank;
import com.example.bank.mapper.BankMapper;
import com.example.bank.repository.BankRepository;
import com.example.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    @Override
    public List<BankDto> findByName(String name) {
        Bank bank = bankRepository.findByName(name);
        if (bank != null) {
            return Collections.singletonList(bankMapper.toDto(bank));
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public BankDto updateBank(Long id, BankDto bankDto) {
        Bank existingBank = bankRepository.findById(id).orElse(null);
        if (existingBank != null) {
            Bank bankToUpdate = bankMapper.toEntity(bankDto);
            existingBank.setName(bankToUpdate.getName());
            Bank updatedBank = bankRepository.save(existingBank);
            return bankMapper.toDto(updatedBank);
        }
        return null;
    }

    @Override
    public void deleteBankById(Long id) {
        bankRepository.deleteById(id);
    }

    @Override
    public BankDto createBank(BankDto bankDto) {
        Bank bankToCreate = bankMapper.toEntity(bankDto);
        Bank createdBank = bankRepository.save(bankToCreate);
        return bankMapper.toDto(createdBank);
    }
}
