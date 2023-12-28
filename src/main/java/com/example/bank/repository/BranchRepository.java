package com.example.bank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.bank.entity.Branch;
import java.util.List;
@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByCity(String city);
    List<Branch> findByPostalCode(String postalCode);
    List<Branch> findByBankIdBank(Long bankIdBank);

    @Query("SELECT b FROM Branch b WHERE b.bankName = :bankName")
    List<Branch> findByBankName(String bankName);
}


