package com.zerobase.fintech.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class SignInDto {

    @Column(unique = true)
    @NotNull
    private String phone;

    @NotNull
    private String password;

}
