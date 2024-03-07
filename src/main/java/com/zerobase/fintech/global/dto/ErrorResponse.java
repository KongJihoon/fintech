package com.zerobase.fintech.global.dto;

import com.zerobase.fintech.global.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private ErrorCode errorCode;
    private String errorMessage;

}
