package com.example.bank.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankDto implements Serializable {
    private Long id;
    private String name;
    private String capitalization;

}
