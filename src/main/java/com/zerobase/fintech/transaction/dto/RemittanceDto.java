package com.zerobase.fintech.transaction.dto;

import com.zerobase.fintech.account.type.Bank;
import lombok.*;

public class RemittanceDto {


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {

        private String remittanceAccountNumber;

        private String receivedAccountNumber;

        private String sendPassword;

        private Long balance;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {

        private String receivedAccountNumber;

        private String receivedName;

        private Bank receivedBank;

        private Long balance;

        public static RemittanceDto.Response from(TransactionDto transactionDto) {


            return Response.builder()
                    .receivedAccountNumber(transactionDto.getReceivedAccount())
                    .receivedName(transactionDto.getReceivedName())
                    .receivedBank(transactionDto.getReceivedBank())
                    .balance(transactionDto.getBalance())
                    .build();
        }


    }

}
