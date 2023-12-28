package com.example.bank.controller;
import com.example.bank.dto.LoansDto;
import com.example.bank.entity.Loans;
import com.example.bank.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoansController {
    private final LoansService loansService;

    @Autowired
    public LoansController(LoansService loansService) {
        this.loansService = loansService;
    }

    @GetMapping("/by-loan-status")
    public List<LoansDto> getLoansByStatus(@RequestParam String loanStatus) {
        return loansService.findByLoanStatus(loanStatus);
    }

    @GetMapping("/by-min-loan-amount")
    public List<LoansDto> getLoansByMinAmount(@RequestParam int minLoanAmount) {
        return loansService.findByMinAmount(minLoanAmount);
    }

    @GetMapping("/by-max-loan-amount")
    public List<LoansDto> getLoansByMaxAmount(@RequestParam int maxLoanAmount) {
        return loansService.findByMaxAmount(maxLoanAmount);
    }

    @PostMapping("/create")
    public LoansDto createLoan(@RequestBody LoansDto loansDto) {
        return loansService.create(loansDto);
    }

    @PutMapping("/update/{id}")
    public LoansDto updateLoan(@PathVariable Long id, @RequestBody LoansDto loansDto) {
        return loansService.update(id, loansDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLoan(@PathVariable Long id) {
        loansService.deleteById(id);
    }
}
