package com.zerobase.fintech.auth.service;

import com.zerobase.fintech.auth.dto.SignInDto;
import com.zerobase.fintech.auth.type.UserType;
import com.zerobase.fintech.global.exception.CustomException;
import com.zerobase.fintech.user.domain.entity.Customer;
import com.zerobase.fintech.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.fintech.global.type.ErrorCode.PASSWORD_NOT_MATCH;
import static com.zerobase.fintech.global.type.ErrorCode.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 고객 정보 확인, 패스워드 일치 확인
     */
    public Customer authenticatedCustomer(SignInDto sign) {
        Customer customer = checkUserPhone(sign.getPhone());

        if (!passwordEncoder.matches(sign.getPassword(), customer.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }
        return customer;

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {


        if (customerRepository.existsByPhone(phone)) {

            Customer customer = checkUserPhone(phone);

            return createUserDetail(customer.getPhone(), customer.getPassword());
        }

        throw new UsernameNotFoundException("User not found with phone" + phone);
    }

    private UserDetails createUserDetail(String phone, String password) {
        return User.withUsername(phone)
                .password(passwordEncoder.encode(password))
                .roles(String.valueOf(UserType.CUSTOMER))
                .build();
    }

    private Customer checkUserPhone(String phone) {

        return customerRepository.findByPhone(phone)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

    }

}
