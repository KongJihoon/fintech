package com.zerobase.fintech.account.dto;


import com.zerobase.fintech.account.type.Bank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {

    private String userName;
    private String accountNumber;
    private Bank bank;
    private Long balance;

}
