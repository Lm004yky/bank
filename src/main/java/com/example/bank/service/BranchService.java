package com.example.bank.service;

import com.example.bank.dto.BranchDto;
import com.example.bank.entity.Branch;

import java.util.List;

public interface BranchService {
    List<BranchDto> findByCity(String city);

    List<BranchDto> findByPostalCode(String postalCode);

    List<BranchDto> findByBankIdBank(Long bankIdBank);

    List<BranchDto> findByBankName(String bankName);

    BranchDto create(BranchDto branchDto);

    BranchDto update(Long id, BranchDto branchDto);

    void deleteById(Long id);
}
