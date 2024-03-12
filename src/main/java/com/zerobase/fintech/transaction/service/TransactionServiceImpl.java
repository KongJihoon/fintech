package com.zerobase.fintech.transaction.service;

import com.zerobase.fintech.account.domain.entity.Account;
import com.zerobase.fintech.account.domain.repository.AccountRepository;
import com.zerobase.fintech.account.type.AccountStatus;
import com.zerobase.fintech.global.exception.CustomException;
import com.zerobase.fintech.global.type.ErrorCode;
import com.zerobase.fintech.transaction.domain.entity.Transaction;
import com.zerobase.fintech.transaction.domain.repository.TransactionRepository;
import com.zerobase.fintech.transaction.dto.DepositDto;
import com.zerobase.fintech.transaction.type.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.zerobase.fintech.account.type.AccountStatus.UNREGISTERED;
import static com.zerobase.fintech.global.type.ErrorCode.ACCOUNT_NOT_FOUND;
import static com.zerobase.fintech.global.type.ErrorCode.UNREGISTERED_ACCOUNT;
import static com.zerobase.fintech.transaction.type.TransactionType.DEPOSIT;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @Override
    @Transactional
    public DepositDto.Response deposit(DepositDto.Request request) {

        Account setAccount = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new CustomException(ACCOUNT_NOT_FOUND));

        if (setAccount.getAccountStatus().equals(UNREGISTERED)) {
            throw new CustomException(UNREGISTERED_ACCOUNT);
        }

        setAccount.setBalance(setAccount.getBalance() + request.getBalance());

        transactionRepository.save(Transaction.builder().
                account(setAccount).
                transactionType(DEPOSIT).
                balance(request.getBalance()).
                depositName(request.getDepositName())
                .build());



        return DepositDto.Response.builder()
                .accountNumber(request.getAccountNumber())
                .depositName(request.getDepositName())
                .balance(request.getBalance())
                .transactionAt(LocalDateTime.now())
                .build();
    }
}
