package com.zerobase.fintech.account.controller;


import com.zerobase.fintech.account.dto.CreateAccount;
import com.zerobase.fintech.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
