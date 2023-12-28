package com.example.bank.controller;
import com.example.bank.dto.BranchDto;
import com.example.bank.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/by-city")
    public List<BranchDto> getBranchesByCity(@RequestParam String city) {
        return branchService.findByCity(city);
    }

    @GetMapping("/by-postal-code")
    public List<BranchDto> getBranchesByPostalCode(@RequestParam String postalCode) {
        return branchService.findByPostalCode(postalCode);
    }

    @GetMapping("/by-bank-id/{id}")
    public List<BranchDto> getBranchesByBankId(@PathVariable(name = "id") Long bankId) {
        return branchService.findByBankIdBank(bankId);
    }

    @GetMapping("/by-bank-name")
    public List<BranchDto> getBranchesByBankName(@RequestParam String bankName) {
        return branchService.findByBankName(bankName);
    }

    @PostMapping("/create")
    public BranchDto createBranch(@RequestBody BranchDto branchDto) {
        return branchService.create(branchDto);
    }

    @PutMapping("/update/{id}")
    public BranchDto updateBranch(@PathVariable Long id, @RequestBody BranchDto branchDto) {
        return branchService.update(id, branchDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBranch(@PathVariable Long id) {
        branchService.deleteById(id);
    }
}
