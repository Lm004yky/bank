package com.example.bank.controller;
import com.example.bank.dto.BankDto;
import com.example.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {
    private final BankService bankService;
    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/by-name")
    public List<BankDto> getBankByName(@RequestParam String name) {
        return bankService.findByName(name);
    }

    @PostMapping("/create")
    public BankDto createBank(@RequestBody BankDto bankDto) {
        return bankService.createBank(bankDto);
    }


    @PutMapping("/update")
    public BankDto updateBank(@RequestParam Long id, @RequestBody BankDto bankDto) {
        return bankService.updateBank(id, bankDto);
    }

    @DeleteMapping("/delete")
    public void deleteBank(@RequestParam Long id) {
        bankService.deleteBankById(id);
    }
}

