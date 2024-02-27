package com.zerobase.fintech.user.service;

import com.zerobase.fintech.auth.dto.SignUpDto;
import com.zerobase.fintech.auth.type.UserType;
import com.zerobase.fintech.global.exception.CustomException;
import com.zerobase.fintech.user.domain.entity.Customer;
import com.zerobase.fintech.user.domain.repository.CustomerRepository;
import com.zerobase.fintech.user.dto.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.fintech.global.type.ErrorCode.ALREADY_EXIST_USER;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final PasswordEncoder encoder;
    private final CustomerRepository customerRepository;

    /**
     * 회원 가입
     */
    @Override
    @Transactional
    public CustomerDto signUp(SignUpDto user) {

        boolean exists = customerRepository.existsByPhone(user.getPhone());

        if(exists){
            throw new CustomException(ALREADY_EXIST_USER);
        }
        user.setPassword(encoder.encode(user.getPassword()));

        Customer setCustomer = customerRepository.save(Customer.builder()
                .userName(user.getUserName())
                .userType(UserType.CUSTOMER)
                .phone(user.getPhone())
                .password(user.getPassword())
                .build());


        return CustomerDto.fromEntity(setCustomer);
    }
}
