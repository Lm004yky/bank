package com.example.bank.service;

import com.example.bank.dto.WorkerDto;
import com.example.bank.entity.Worker;

import java.util.List;

public interface WorkerService {
    List<WorkerDto> findByCity(String city);

    List<WorkerDto> findByPosition(String position);

    List<WorkerDto> findByMinSalary(int minSalary);

    List<WorkerDto> findByMaxSalary(int maxSalary);

    List<WorkerDto> getAllWorkers();

    WorkerDto getWorkerById(Long id);
}
