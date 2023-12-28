package com.example.bank.service.impl;

import com.example.bank.dto.WorkerDto;
import com.example.bank.entity.Worker;
import com.example.bank.mapper.WorkerMapper;
import com.example.bank.repository.WorkerRepository;
import com.example.bank.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository,
                             WorkerMapper workerMapper) {
        this.workerRepository = workerRepository;
        this.workerMapper = workerMapper;
    }

    @Override
    public List<WorkerDto> findByCity(String city) {
        List<Worker> workers = workerRepository.findByCity(city);
        return workerMapper.toDto(workers);
    }
    @Override
    public List<WorkerDto> findByPosition(String position) {
        List<Worker> workers = workerRepository.findByPosition(position);
        return workerMapper.toDto(workers);
    }
    @Override
    public List<WorkerDto> findByMinSalary(int minSalary) {
        List<Worker> workers = workerRepository.findByMinSalary(minSalary);
        return workerMapper.toDto(workers);
    }

    @Override
    public List<WorkerDto> findByMaxSalary(int maxSalary) {
        List<Worker> workers = workerRepository.findByMaxSalary(maxSalary);
        return workerMapper.toDto(workers);
    }

    @Override
    public List<WorkerDto> getAllWorkers() {
        return workerMapper.toDto(workerRepository.findAll());
    }

    @Override
    public WorkerDto getWorkerById(Long id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.map(workerMapper::toDto).orElse(null);
    }

}
