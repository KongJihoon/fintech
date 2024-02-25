package com.zerobase.fintech.user.controller;

import com.zerobase.fintech.auth.dto.SignUpDto;
import com.zerobase.fintech.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/user/signUp")
    public ResponseEntity<?> userSignUp(@RequestBody SignUpDto sign){

        return ResponseEntity.ok().body(
                sign.from(customerService.signUp(sign))
        );
    }

}
