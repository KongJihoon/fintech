package com.zerobase.fintech.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zerobase.fintech.account.type.Bank;
import com.zerobase.fintech.global.exception.CustomException;
import com.zerobase.fintech.global.type.ErrorCode;
import com.zerobase.fintech.transaction.domain.entity.Transaction;
import com.zerobase.fintech.transaction.type.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;

    private TransactionType transactionType;

    private String accountNumber;

    private Long balance;

    private String depositName;

    private String withdrawName;

    private String receivedName;

    private String receivedAccount;

    private Bank receivedBank;

    private String targetName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime transactionAt;


    public static TransactionDto fromEntity(Transaction transaction) {

        return TransactionDto.builder()
                .id(transaction.getId())
                .transactionType(transaction.getTransactionType())
                .accountNumber(transaction.getAccount().getAccountNumber())
                .balance(transaction.getBalance())
                .depositName(transaction.getDepositName())
                .withdrawName(transaction.getWithdrawName())
                .receivedName(transaction.getReceivedName())
                .receivedAccount(transaction.getReceivedAccount())
                .receivedBank(transaction.getReceivedBank())
                .transactionAt(transaction.getTransactionAt())
                .build();
    }


    public String getUserName(TransactionType transactionType) {

        switch (transactionType) {

            case DEPOSIT:
                return depositName;

            case WITHDRAW:
                return withdrawName;

            case REMITTANCE:
                return receivedName;

            default: throw new CustomException(ErrorCode.TRANSACTION_TYPE_NOT_EXIST);
        }

    }



}
