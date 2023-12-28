package com.example.bank.service.impl;
import com.example.bank.dto.ClientDto;
import com.example.bank.entity.Branch;
import com.example.bank.entity.Client;
import com.example.bank.mapper.ClientMapper;
import com.example.bank.repository.ClientRepository;
import com.example.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDto> findByGender(String gender) {
        List<Client> clients = clientRepository.findByGender(gender);
        return clientMapper.toDtoList(clients);
    }
    @Override
    public List<ClientDto> findByBranchId(Long branchId) {
        List<Client> clients = clientRepository.findByBranchIdBranch(branchId);
        return clientMapper.toDtoList(clients);
    }
    @Override
    public List<ClientDto> findClientNameSurname(String name, String surname) {
        List<Client> clients = clientRepository.findByClientNameAndClientSurname(name, surname);
        return clientMapper.toDtoList(clients);
    }
    @Override
    public List<ClientDto> findByBirthDate(String birthDate) {
        List<Client> clients = clientRepository.findByBirthDate(birthDate);
        return clientMapper.toDtoList(clients);
    }



    @Override
    public ClientDto create(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    public ClientDto update(Long id, ClientDto clientDto) {
        Optional<Client> existingClientOptional = clientRepository.findById(id);
        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();
            if (clientDto.getAddress() != null) {
                existingClient.setAddress(clientDto.getAddress());
            }
            if (clientDto.getGender() != null) {
                existingClient.setGender(clientDto.getGender());
            }
            if (clientDto.getBirthDate() != null) {
                existingClient.setBirthDate(clientDto.getBirthDate());
            }
            Client savedClient = clientRepository.save(existingClient);
            return clientMapper.toDto(savedClient);
        }
        return null;
    }




    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
