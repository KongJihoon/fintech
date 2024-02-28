package com.zerobase.fintech.global.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     *  common error
     */
    INVALID_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),

    /**
     * User
     */
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST.value(), "이미 가입된 회원입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "회원을 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), "패스워드가 일치하지 않습니다."),

    /**
     * account
     */
    MAX_ACCOUNT_PER_USER_10(HttpStatus.BAD_REQUEST.value(), "사용자 최대 계좌는 10개 입니다.");


    private final int status;
    private final String description;

}
