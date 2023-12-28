package com.example.bank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "worker")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "worker_name")
    private String worker_name;
    @Column(name = "worker_surname")
    private String worker_surname;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @Column(name = "position")
    private String position;
    @Column(name = "hire_date")
    private String hireDate;
    @Column(name = "salary")
    private int salary;
    @Column(name = "branch_id_branch")
    private Long branchIdBranch;
}


