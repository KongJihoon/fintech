package com.zerobase.fintech.transaction.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class WithDrawDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {

        @NotBlank(message = "출금자명을 입력해주세요.")
        private String withDrawName;

        @NotBlank(message = "계좌번호를 입력해주세요.")
        private String accountNumber;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        @Min(value = 1000L, message = "최소 출금금액은 1000원 입니다.")
        private Long balance;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String withDrawName;

        private String accountNumber;

        private Long balance;

        private LocalDateTime transactionAt;

        public static WithDrawDto.Response response(WithDrawDto.Request request) {


            return Response.builder()
                    .withDrawName(request.withDrawName)
                    .accountNumber(request.accountNumber)
                    .balance(request.balance)
                    .transactionAt(LocalDateTime.now())
                    .build();

        }


    }
}
