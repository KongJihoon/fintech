package com.zerobase.fintech.transaction.dto;

import com.zerobase.fintech.transaction.type.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionInfo {

    private String userName;

    private TransactionType transactionType;

    private Long balance;

    private LocalDateTime transactionAt;


}
