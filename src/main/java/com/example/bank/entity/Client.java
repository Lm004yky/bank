package com.example.bank.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "client_surname")
    private String clientSurname;
    @Column(name = "address")
    private String address;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "salary")
    private int salary;
    @Column(name = "branch_id_branch")
    private Long branchIdBranch;
}


