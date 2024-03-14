package com.zerobase.fintech.transaction.service;

import com.zerobase.fintech.account.domain.entity.Account;
import com.zerobase.fintech.account.domain.repository.AccountRepository;
import com.zerobase.fintech.global.exception.CustomException;
import com.zerobase.fintech.transaction.domain.entity.Transaction;
import com.zerobase.fintech.transaction.domain.repository.TransactionRepository;
import com.zerobase.fintech.transaction.dto.DepositDto;
import com.zerobase.fintech.transaction.dto.WithDrawDto;
import com.zerobase.fintech.user.domain.entity.Customer;
import com.zerobase.fintech.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.fintech.global.type.ErrorCode.*;
import static com.zerobase.fintech.transaction.type.TransactionType.DEPOSIT;
import static com.zerobase.fintech.transaction.type.TransactionType.WITHDRAW;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public DepositDto.Response deposit(DepositDto.Request request) {

        Account setAccount = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new CustomException(ACCOUNT_NOT_FOUND));

        if (setAccount.isUnregistered()) {
            throw new CustomException(UNREGISTERED_ACCOUNT);
        }

        setAccount.depositBalance(request.getBalance());

        transactionRepository.save(Transaction.builder().
                account(setAccount).
                transactionType(DEPOSIT).
                balance(request.getBalance()).
                depositName(request.getDepositName())
                .build());



        return DepositDto.Response.response(request);
    }

    @Override
    @Transactional
    public WithDrawDto.Response withdraw(WithDrawDto.Request request) {

        Account setAccount = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (setAccount.isUnregistered()) {
            throw new CustomException(UNREGISTERED_ACCOUNT);
        }

        Customer customer = customerRepository.findById(setAccount.getCustomer().getId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));


        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        setAccount.withDrawBalance(request.getBalance());

        transactionRepository.save(Transaction.builder().
                account(setAccount).
                withdrawName(request.getWithDrawName()).
                balance(request.getBalance()).
                transactionType(WITHDRAW).
                build());

        return WithDrawDto.Response.response(request);
    }
}
