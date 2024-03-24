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
    INVALID_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류가 발생했습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    /**
     * User
     */
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "패스워드가 일치하지 않습니다."),

    /**
     * account
     */
    MAX_ACCOUNT_PER_USER_10(HttpStatus.BAD_REQUEST, "사용자 최대 계좌는 10개 입니다."),
    USER_ACCOUNT_UN_MATCH(HttpStatus.BAD_REQUEST, "고객과 계좌가 일치하지 않습니다."),
    ACCOUNT_NOT_FOUND(HttpStatus.BAD_REQUEST, "계좌를 찾을 수 없습니다."),
    ACCOUNT_ALREADY_UNREGISTERED(HttpStatus.BAD_REQUEST, "이미 해지된 계좌입니다."),
    BALANCE_NOT_EMPTY(HttpStatus.BAD_REQUEST, "계좌의 잔액이 남아있습니다."),
    ONLY_REGISTERED_OTHER_BANK(HttpStatus.BAD_REQUEST, "등록은 다른 은행의 계좌만 가능합니다."),
    ALREADY_EXIST_ACCOUNT_NUMBER(HttpStatus.BAD_REQUEST, "이미 존재하는 계좌 번호입니다."),
    EMPTY_REQUEST(HttpStatus.BAD_REQUEST, "요청 값을 제대로 입력해주세요."),
    UNREGISTERED_ACCOUNT(HttpStatus.BAD_REQUEST, "해지된 계좌입니다."),
    BALANCE_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),
    TRANSACTION_TYPE_NOT_EXIST(HttpStatus.BAD_REQUEST, "거래 종류가 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String description;

}
