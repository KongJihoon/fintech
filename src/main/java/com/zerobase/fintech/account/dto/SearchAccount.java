package com.zerobase.fintech.account.dto;

import com.zerobase.fintech.account.type.Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SearchAccount {


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull(message = "은행명을 입력헤주세요.")
        private Bank bank;

        @NotBlank(message = "계좌번호를 입력해주세요.")
        @Size(min = 11, max = 13, message = "계좌번호는 11~13자리 입니다.")
        private String accountNumber;

    }

}
