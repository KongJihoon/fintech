package com.zerobase.fintech.account.controller;


import com.zerobase.fintech.account.dto.*;
import com.zerobase.fintech.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    /**
     * 계좌 생성
     */
    @PostMapping("/account")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateAccount.Response createAccount(
            @RequestBody @Valid CreateAccount.Request request) {
        return CreateAccount.Response.from(
                accountService.createAccount(request)
        );
    }


    /**
     * 계좌 해지
     */
    @DeleteMapping("/account")
    @PreAuthorize("hasRole('CUSTOMER')")
    public DeleteAccount.Response deleteAccount(
            @RequestBody @Valid DeleteAccount.Request request) {
        return DeleteAccount.Response.from(
                accountService.deleteAccount(request.getUserId(),request.getAccountNumber())
        );
    }


    /**
     * 계좌 조회
     */

    @GetMapping("/account/info")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<AccountInfo> getAccountByUserId( @RequestParam("userId") @Valid Long userId) {
        return accountService.getAccountByUserId(userId)
                .stream().map(accountDto ->
                        AccountInfo.builder()
                                .userName(accountDto.getUserName())
                                .accountNumber(accountDto.getAccountNumber())
                                .bank(accountDto.getBank())
                                .balance(accountDto.getBalance()).build())
                .collect(Collectors.toList());
    }

    @GetMapping("/account/search")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<AccountInfo> SearchAccount(@RequestBody @Valid SearchAccount.Request request) {
        return accountService.getAccountByCustomer(request)
                .stream().map(accountDto ->
                        AccountInfo.builder()
                                .userName(accountDto.getUserName())
                                .accountNumber(accountDto.getAccountNumber())
                                .bank(accountDto.getBank())
                                .balance(accountDto.getBalance()).build())
                .collect(Collectors.toList());
    }


    @PostMapping("/account/other")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> registerOtherBankAccount(
            @RequestBody @Valid OtherBankAccountCreate.Request request) {
        return ResponseEntity.ok(accountService.registerOtherBankAccount(request));
    }


}
