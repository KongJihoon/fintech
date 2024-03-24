package com.zerobase.fintech.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class TransactionListDto {

    @Getter
    @Setter
    public static class Request {


        @NotBlank(message = "계좌번호를 입력해주세요.")
        private String accountNumber;

        private String password;


    }
}
