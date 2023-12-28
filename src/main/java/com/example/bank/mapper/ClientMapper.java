package com.example.bank.mapper;
import com.example.bank.dto.BranchDto;
import com.example.bank.dto.ClientDto;
import com.example.bank.entity.Branch;
import com.example.bank.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDto clientDTO);
    ClientDto toDto(Client client);

    List<ClientDto> toDtoList(List<Client> gender);
    List<Client> toEntityList(List<ClientDto> gender);
}
