package com.example.bank.mapper;
import com.example.bank.dto.LoansDto;
import com.example.bank.entity.Loans;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoansMapper {
    Loans toEntity(LoansDto loansDTO);
    LoansDto toDto(Loans loans);
    List<LoansDto> toDtoList(List<Loans> loans);
}
