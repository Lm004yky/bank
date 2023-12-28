package com.example.bank.service.impl;
import com.example.bank.dto.LoansDto;
import com.example.bank.entity.Loans;
import com.example.bank.mapper.LoansMapper;
import com.example.bank.repository.LoansRepository;
import com.example.bank.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoansServiceImpl implements LoansService {

    private final LoansRepository loansRepository;
    private final LoansMapper loansMapper;

    @Autowired
    public LoansServiceImpl(LoansRepository loansRepository, LoansMapper loansMapper) {
        this.loansRepository = loansRepository;
        this.loansMapper = loansMapper;
    }

    @Override
    public List<LoansDto> findByLoanStatus(String loanStatus) {
        List<Loans> loans = loansRepository.findByLoanStatus(loanStatus);
        return loansMapper.toDtoList(loans);
    }
    @Override
    public List<LoansDto> findByMinAmount(int minLoanAmount) {
        List<Loans> loans = loansRepository.findByMinAmount(minLoanAmount);
        return loansMapper.toDtoList(loans);
    }
    @Override
    public List<LoansDto> findByMaxAmount(int maxLoanAmount) {
        List<Loans> loans = loansRepository.findByMaxAmount(maxLoanAmount);
        return loansMapper.toDtoList(loans);
    }

    @Override
    public LoansDto create(LoansDto loanDto) {
        Loans loanToCreate = loansMapper.toEntity(loanDto);
        Loans createdLoan = loansRepository.save(loanToCreate);
        return loansMapper.toDto(createdLoan);
    }

    @Override
    public LoansDto update(Long id, LoansDto loanDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        loansRepository.deleteById(id);
    }
}

