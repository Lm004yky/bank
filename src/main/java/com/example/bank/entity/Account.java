package com.example.bank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status")
    private String status;
    @Column(name = "open_date")
    private String openDate;
    @Column(name = "close_date")
    private String closeDate;
    @Column(name = "balance")
    private int balance;
    @Column(name = "client_id_client")
    private Long clientIdClient;
    @Column(name = "phone_number")
    private String phone_number;
}


