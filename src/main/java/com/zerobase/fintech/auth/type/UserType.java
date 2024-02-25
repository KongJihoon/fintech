package com.zerobase.fintech.auth.type;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {

    CUSTOMER("ROLE_CUSTOMER");

    private final String description;
}
