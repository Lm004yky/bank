package com.example.bank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bank.entity.Bank;
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByName(String name);

}
