package com.example.bank.service;
import com.example.bank.dto.ClientDto;
import com.example.bank.entity.Client;
import java.util.List;

public interface ClientService {
    List<ClientDto> findByGender(String gender);

    List<ClientDto> findByBranchId(Long branchIdBranch);

    List<ClientDto> findClientNameSurname(String name, String surname);

    List<ClientDto> findByBirthDate(String birthDate);

    ClientDto create(ClientDto clientDto);

    ClientDto update(Long id, ClientDto clientDto);

    void deleteById(Long id);
}
