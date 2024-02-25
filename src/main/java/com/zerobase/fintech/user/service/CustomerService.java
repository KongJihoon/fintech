package com.zerobase.fintech.user.service;

import com.zerobase.fintech.auth.dto.SignUpDto;
import com.zerobase.fintech.user.dto.CustomerDto;

public interface CustomerService {

    CustomerDto signUp(SignUpDto user);

}
