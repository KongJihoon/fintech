package com.zerobase.fintech.account.dto;

import com.zerobase.fintech.account.type.Bank;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateAccount {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private Long userId;

        @NotNull
        @Min(0)
        private Long initialBalance;

        private String accountName;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long accountId;
        private String accountNumber;
        private Bank bank;
        private LocalDateTime createdAt;

        public static Response from(AccountDto accountDto) {
            return Response.builder()
                    .accountId(accountDto.getUserId())
                    .accountNumber(accountDto.getAccountNumber())
                    .bank(accountDto.getBank())
                    .createdAt(accountDto.getCreatedAt()).build();
        }

    }

}
