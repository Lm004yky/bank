package com.example.bank.service.impl;
import com.example.bank.mapper.BranchMapper;
import com.example.bank.dto.BranchDto;
import com.example.bank.entity.Branch;
import com.example.bank.repository.BranchRepository;
import com.example.bank.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    public List<BranchDto> findByCity(String city) {
        List<Branch> branches = branchRepository.findByCity(city);
        return branchMapper.toDtoList(branches);
    }

    @Override
    public List<BranchDto> findByPostalCode(String postalCode) {
        List<Branch> branches = branchRepository.findByPostalCode(postalCode);
        return branchMapper.toDtoList(branches);
    }

    @Override
    public List<BranchDto> findByBankIdBank(Long bankIdBank) {
        List<Branch> branches = branchRepository.findByBankIdBank(bankIdBank);
        return branchMapper.toDtoList(branches);
    }

    @Override
    public List<BranchDto> findByBankName(String bankName) {
        List<Branch> branches = branchRepository.findByBankName(bankName);
        return branchMapper.toDtoList(branches);
    }


    @Override
    public BranchDto create(BranchDto branchDto) {
        Branch branch = branchMapper.toEntity(branchDto);
        Branch savedBranch = branchRepository.save(branch);
        return branchMapper.toDto(savedBranch);
    }

    @Override
    public BranchDto update(Long id, BranchDto branchDto) {
        Optional<Branch> existingBranchOptional = branchRepository.findById(id);
        if (existingBranchOptional.isPresent()) {
            Branch existingBranch = existingBranchOptional.get();
            Branch updatedBranch = branchMapper.toEntity(branchDto);
            updatedBranch.setId(existingBranch.getId());
            updatedBranch = branchRepository.save(updatedBranch);
            return branchMapper.toDto(updatedBranch);
        }
        return null;
    }


    @Override
    public void deleteById(Long id) {
        branchRepository.deleteById(id);
    }
}
