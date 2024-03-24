package com.zerobase.fintech.global.exception;

import com.zerobase.fintech.global.dto.ErrorResponse;
import com.zerobase.fintech.global.type.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.zerobase.fintech.global.type.ErrorCode.INVALID_REQUEST;
import static com.zerobase.fintech.global.type.ErrorCode.INVALID_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e) {

        ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
        return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> dataIntegrityViolationException(DataIntegrityViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
        return new ResponseEntity<>(errorResponse, INVALID_REQUEST.getHttpStatus());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNameNotFoundException(UsernameNotFoundException e) {

        ErrorResponse errorResponse = new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getDescription());
        return new ResponseEntity<>(errorResponse, INVALID_REQUEST.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {


        ErrorResponse errorResponse = new ErrorResponse(INVALID_SERVER_ERROR, INVALID_SERVER_ERROR.getDescription());
        return new ResponseEntity<>(errorResponse, INVALID_SERVER_ERROR.getHttpStatus());
    }

}
