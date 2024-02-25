package com.zerobase.fintech.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * User
     */
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST.value(), "이미 가입된 회원입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "회원을 찾을 수 없습니다.");

    private final int status;
    private final String description;

}
