package com.zerobase.fintech.transaction.controller;

import com.zerobase.fintech.transaction.dto.DepositDto;
import com.zerobase.fintech.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;


    @PutMapping("/transaction")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> deposit(@RequestBody DepositDto.Request request) {
        DepositDto.Response response = transactionService.deposit(request);
        return ResponseEntity.ok(response);
    }

}
