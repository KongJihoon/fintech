package com.zerobase.fintech.account.controller;


import com.zerobase.fintech.account.dto.AccountInfo;
import com.zerobase.fintech.account.dto.CreateAccount;
import com.zerobase.fintech.account.dto.DeleteAccount;
import com.zerobase.fintech.account.service.AccountService;
import lombok.RequiredArgsConstructor;
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


    @PostMapping("/account/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public CreateAccount.Response createAccount(
            @RequestBody CreateAccount.Request request
    ){
        return CreateAccount.Response.from(
                accountService.createAccount(request)
        );
    }

    @DeleteMapping("/account/delete")
    @PreAuthorize("hasRole('CUSTOMER')")
    public DeleteAccount.Response deleteAccount(
            @RequestBody @Valid DeleteAccount.Request request
    ){
        return DeleteAccount.Response.from(
                accountService.deleteAccount(request.getUserId(),request.getAccountNumber())
        );
    }

    @GetMapping("/account/info")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<AccountInfo> getAccountByUserId(
            @RequestParam("userId") Long userId
    ){
        return accountService.getAccountByUserId(userId)
                .stream().map(accountDto ->
                        AccountInfo.builder()
                                .accountNumber(accountDto.getAccountNumber())
                                .balance(accountDto.getBalance()).build())
                .collect(Collectors.toList());
    }


}
