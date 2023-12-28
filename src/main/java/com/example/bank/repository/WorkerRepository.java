package com.example.bank.repository;

import com.example.bank.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    List<Worker> findByCity(String city);

    List<Worker> findByPosition(String position);

    @Query("SELECT w FROM Worker w WHERE w.salary >= :minSalary")
    List<Worker> findByMinSalary(@Param("minSalary") int minSalary);
    @Query("SELECT w FROM Worker w WHERE w.salary >= :maxSalary")
    List<Worker> findByMaxSalary(int maxSalary);
}
