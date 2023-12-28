package com.example.bank.controller;

import com.example.bank.dto.AccountDto;
import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/by-status")
    public List<AccountDto> getAccountsByStatus(@RequestParam String status,
                                                @CookieValue(value = "lang", defaultValue = "ru") String lang)
    {
        return accountService.findByStatus(status, lang);
    }

    @GetMapping("/by-min-balance")
    public List<AccountDto> getMinBalance(@RequestParam int minBalance) {
        return accountService.findByMinBalance(minBalance);
    }

    @GetMapping("/by-max-balance")
    public List<AccountDto> getMaxBalance(@RequestParam int maxBalance) {
        return accountService.findByMaxBalance(maxBalance);
    }

    @GetMapping("/by-open-date")
    public List<AccountDto> getAccountsByOpenDate(@RequestParam String openDate) {
        return accountService.findByOpenDate(openDate);
    }

    @GetMapping("/by-close-date")
    public List<AccountDto> getAccountsByCloseDate(@RequestParam String closeDate) {
        return accountService.findByCloseDate(closeDate);
    }

    @GetMapping("/by-phone-number")
    public List<AccountDto> getAccountsByPhoneNumber(@RequestHeader("phoneNumber") String phoneNumber) {
        return accountService.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/{id}")
    public List<AccountDto> getAccountById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.create(account);
    }

    @DeleteMapping("/delete")
    public void deleteAccount(@RequestParam Long id) {
        accountService.deleteById(id);
    }
}