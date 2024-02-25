package com.zerobase.fintech.auth.dto;

import com.zerobase.fintech.user.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignUpDto {

    private String userName;
    private String phone;
    private String password;

    public SignUpDto from(CustomerDto customerDto){
        return SignUpDto.builder()
                .userName(customerDto.getUserName())
                .phone(customerDto.getPhone())
                .password(customerDto.getPassword())
                .build();
    }
}
