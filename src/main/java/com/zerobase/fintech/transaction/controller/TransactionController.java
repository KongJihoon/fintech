package com.zerobase.fintech.transaction.controller;

import com.zerobase.fintech.transaction.dto.*;
import com.zerobase.fintech.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;


    @PutMapping("/transaction")
    @PreAuthorize("hasRole('CUSTOMER')")
    public DepositDto.Response deposit(@RequestBody @Valid DepositDto.Request request) {
        return DepositDto.Response.from(
                transactionService.deposit(request)
        );
    }

    @PutMapping("/transaction/withdraw")
    @PreAuthorize("hasRole('CUSTOMER')")
    public WithDrawDto.Response withdraw(@RequestBody @Valid WithDrawDto.Request request) {

        return WithDrawDto.Response.from(
                transactionService.withdraw(request)
        );
    }

    @PutMapping("/transaction/remittance")
    @PreAuthorize("hasRole('CUSTOMER')")
    public RemittanceDto.Response remittance(@RequestBody @Valid RemittanceDto.Request request) {
        return RemittanceDto.Response.from(
                transactionService.remittance(request)
        );
    }

    @GetMapping("/transaction/info")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<TransactionInfo> getAccountNumber(
            @RequestBody @Valid TransactionListDto.Request request
    ) {
        return transactionService.getTransaction(request)
                .stream().map(transactionDto ->
                        TransactionInfo.builder()
                                .userName(transactionDto.getUserName(transactionDto.getTransactionType()))
                                .transactionType(transactionDto.getTransactionType())
                                .transactionAt(transactionDto.getTransactionAt())
                                .balance(transactionDto.getBalance())
                                .build()).collect(Collectors.toList());
    }

}
