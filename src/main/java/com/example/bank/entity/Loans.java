package com.example.bank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "loan_status")
    private String loanStatus;
    @Column(name = "loan_open_date")
    private String openDate;
    @Column(name = "loan_close_date")
    private String closeDate;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "loan_amount")
    private int loanAmount;
    @Column(name = "account_id_account")
    private Long accountIdAccount;
}


