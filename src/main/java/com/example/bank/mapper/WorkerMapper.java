package com.example.bank.mapper;
import com.example.bank.dto.WorkerDto;
import com.example.bank.entity.Worker;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkerMapper {
    Worker toEntity(WorkerDto workerDTO);
    WorkerDto toDto(Worker worker);
    List<Worker> toEntity (List<WorkerDto> workerDtoList);
    List<WorkerDto> toDto(List<Worker> workers);
}
