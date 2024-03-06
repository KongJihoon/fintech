package com.zerobase.fintech.user.controller;

import com.zerobase.fintech.auth.dto.SignInDto;
import com.zerobase.fintech.auth.dto.SignUpDto;
import com.zerobase.fintech.auth.security.TokenProvider;
import com.zerobase.fintech.auth.service.AuthService;
import com.zerobase.fintech.user.domain.entity.Customer;
import com.zerobase.fintech.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @PostMapping("/user/signUp")
    public ResponseEntity<?> customerSignUp(@RequestBody @Valid SignUpDto sign) {

        return ResponseEntity.ok().body(
                sign.from(customerService.signUp(sign))
        );
    }

    @PostMapping("/user/signIn")
    public ResponseEntity<?> customerSignIn(@RequestBody @Valid SignInDto sign) {
        Customer customer = authService.authenticatedCustomer(sign);

        return ResponseEntity.ok(
                tokenProvider.createToken(
                        customer.getPhone(),
                        customer.getUserType()
                )
        );

    }


}
