package com.example.bank.mapper;
import com.example.bank.dto.BranchDto;
import com.example.bank.entity.Branch;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    Branch toEntity(BranchDto branchDto);
    BranchDto toDto(Branch branch);
    List<BranchDto> toDtoList(List<Branch> branches);
}
