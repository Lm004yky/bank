package com.example.bank.controller;
import com.example.bank.dto.WorkerDto;
import com.example.bank.entity.Worker;
import com.example.bank.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/workers")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/all")
    public List<WorkerDto> getAllWorkers() {
        return workerService.getAllWorkers();
    }

    @GetMapping("/{id}")
    public WorkerDto getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(id);
    }


    @GetMapping("/by-city/")
    public List<WorkerDto> getWorkersByCity(@RequestParam String city) {
        return workerService.findByCity(city);
    }

    @GetMapping("/by-position")
    public List<WorkerDto> getWorkersByPosition(@RequestParam String position) {
        return workerService.findByPosition(position);
    }

    @GetMapping("/by-min-salary")
    public List<WorkerDto> getWorkersByMinSalary(@RequestParam int minSalary) {
        return workerService.findByMinSalary(minSalary);
    }

    @GetMapping("/by-max-salary")
    public List<WorkerDto> getWorkersByMaxSalary(@RequestParam int maxSalary) {
        return workerService.findByMaxSalary(maxSalary);
    }


}
