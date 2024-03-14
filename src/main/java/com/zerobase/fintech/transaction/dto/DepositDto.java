package com.zerobase.fintech.transaction.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class DepositDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        @NotBlank(message = "계좌번호를 입력해주세요.")
        private String accountNumber;

        @NotBlank(message = "입금자명을 입력해주세요.")
        private String depositName;

        @Min(value = 1000L, message = "최소 입금금액은 1000원 입니다.")
        private Long balance;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String accountNumber;

        private String depositName;

        private Long balance;

        private LocalDateTime transactionAt;

        public static Response response(DepositDto.Request request) {

            return DepositDto.Response.builder()
                    .accountNumber(request.getAccountNumber())
                    .depositName(request.getDepositName())
                    .balance(request.getBalance())
                    .transactionAt(LocalDateTime.now())
                    .build();
        }


    }



}
