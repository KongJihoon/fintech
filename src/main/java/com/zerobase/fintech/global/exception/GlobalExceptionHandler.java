package com.zerobase.fintech.global.exception;

import com.zerobase.fintech.global.dto.ErrorResponse;
import com.zerobase.fintech.global.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.zerobase.fintech.global.type.ErrorCode.INVALID_REQUEST;
import static com.zerobase.fintech.global.type.ErrorCode.INVALID_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ErrorResponse customExceptionHandler(CustomException e){
        return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse dataIntegrityViolationException(DataIntegrityViolationException e){
        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponse userNameNotFoundException(UsernameNotFoundException e){
        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
    }

//    @ExceptionHandler(Exception.class)
//    public ErrorResponse exceptionHandler(Exception e){
//        return new ErrorResponse(INVALID_SERVER_ERROR, INVALID_SERVER_ERROR.getDescription());
//    }

}
