package com.zerobase.fintech.account.dto;


import com.zerobase.fintech.account.domain.entity.Account;
import com.zerobase.fintech.account.type.Bank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long userId;

    private String userName;

    private String accountNumber;

    private Long balance;

    private Bank bank;

    private LocalDateTime createdAt;

    private LocalDateTime unRegisteredAt;

    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder()
                .userId(account.getId())
                .userName(account.getCustomer().getUsername())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .bank(account.getBank())
                .createdAt(account.getCreatedAt())
                .unRegisteredAt(account.getUnRegisteredAt())
                .build();



    }

}
