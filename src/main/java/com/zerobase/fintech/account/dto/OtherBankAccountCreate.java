package com.zerobase.fintech.account.dto;

import com.zerobase.fintech.account.type.Bank;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


public class OtherBankAccountCreate {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull
        private Long userId;

        @NotNull(message = "은행명을 입력해주세요.")
        private Bank bank;

        @NotBlank(message = "계좌 이름을 입력해주세요.")
        private String accountName;

        @NotBlank(message = "계좌번호를 입력해주세요.")
        @Size(min = 11, max = 13, message = "계좌번호는 11~13자리 입니다.")
        private String accountNumber;

        @NotNull
        @Min(0)
        private Long initialBalance;

    }
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long accountId;

        private Bank bank;

        private String accountNumber;

        private Long balance;

        private LocalDateTime regDt;


        public static Response from(AccountDto accountDto) {
            return Response.builder()
                    .accountId(accountDto.getUserId())
                    .bank(accountDto.getBank())
                    .accountNumber(accountDto.getAccountNumber())
                    .balance(accountDto.getBalance())
                    .regDt(accountDto.getCreatedAt())
                    .build();
        }


    }




}
