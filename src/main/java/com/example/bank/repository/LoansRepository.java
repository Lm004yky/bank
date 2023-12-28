package com.example.bank.repository;

import com.example.bank.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {

    List<Loans> findByLoanStatus(String loanStatus);

    @Query("SELECT l FROM Loans l WHERE l.loanAmount <= :maxLoanAmount")
    List<Loans> findByMaxAmount(@Param("maxLoanAmount") int maxLoanAmount);

    @Query("SELECT l FROM Loans l WHERE l.loanAmount >= :minLoanAmount")
    List<Loans> findByMinAmount(@Param("minLoanAmount") int minLoanAmount);
}
