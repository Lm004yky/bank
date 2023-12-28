package com.example.bank.repository;

import com.example.bank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByGender(String gender);
    List<Client> findByBranchIdBranch(Long branchIdBranch);
    List<Client> findByClientNameAndClientSurname(String clientName, String clientSurname);
    List<Client> findByBirthDate(String birthDate);
}
