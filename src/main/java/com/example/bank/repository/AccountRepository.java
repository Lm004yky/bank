package com.example.bank.repository;

import com.example.bank.dto.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bank.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByStatus(String status);

    List<Account> findByBalanceGreaterThanEqual(int minBalance);

    List<Account> findByBalanceLessThanEqual(int maxBalance);

    List<Account> findByOpenDate(String openDate);

    List<Account> findByCloseDate(String closeDate);

}
