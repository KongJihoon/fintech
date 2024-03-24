package com.zerobase.fintech.transaction.service;

import com.zerobase.fintech.account.domain.entity.Account;
import com.zerobase.fintech.account.domain.repository.AccountRepository;
import com.zerobase.fintech.global.exception.CustomException;
import com.zerobase.fintech.transaction.domain.entity.Transaction;
import com.zerobase.fintech.transaction.domain.repository.TransactionRepository;
import com.zerobase.fintech.transaction.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.zerobase.fintech.global.type.ErrorCode.*;
import static com.zerobase.fintech.transaction.type.TransactionType.*;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public TransactionDto deposit(DepositDto.Request request) {

        Account setAccount = getAccountNumber(request.getAccountNumber());


        setAccount.increaseBalance(request.getBalance());




        return TransactionDto.fromEntity(
                transactionRepository.save(Transaction.builder().
                        account(setAccount).
                        transactionType(DEPOSIT).
                        balance(request.getBalance()).
                        depositName(request.getDepositName()).
                        transactionAt(LocalDateTime.now())
                        .build())
        );
    }

    @Override
    @Transactional
    public TransactionDto withdraw(WithDrawDto.Request request) {

        Account setAccount = getAccountNumber(request.getAccountNumber());



        if (!passwordEncoder.matches(request.getPassword(), setAccount.getCustomer().getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        setAccount.reductionBalance(request.getBalance());



        return TransactionDto.fromEntity(transactionRepository.save(Transaction.builder().
                account(setAccount).
                withdrawName(request.getWithDrawName()).
                balance(request.getBalance()).
                transactionType(WITHDRAW).
                transactionAt(LocalDateTime.now()).
                build()));
    }

    @Override
    @Transactional
    public TransactionDto remittance(RemittanceDto.Request request) {

        Account remittanceAccount = getAccountNumber(request.getRemittanceAccountNumber());

        Account receivedAccount = getAccountNumber(request.getReceivedAccountNumber());

        if (!passwordEncoder.matches(request.getSendPassword(), remittanceAccount.getCustomer().getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        if (request.getBalance() > remittanceAccount.getBalance()) {
            throw new CustomException(BALANCE_NOT_ENOUGH);
        }

        remittanceAccount.reductionBalance(request.getBalance());

        receivedAccount.increaseBalance(request.getBalance());


        return TransactionDto.fromEntity(transactionRepository.save(Transaction.builder().
                account(remittanceAccount).
                receivedName(receivedAccount.getCustomer().getUsername()).
                receivedAccount(receivedAccount.getAccountNumber()).
                receivedBank(receivedAccount.getBank()).
                balance(request.getBalance()).
                transactionType(REMITTANCE).
                transactionAt(LocalDateTime.now()).
                build()));
    }

    @Override
    @Transactional
    public List<TransactionDto> getTransaction(TransactionListDto.Request request) {

        Account account = getAccountNumber(request.getAccountNumber());


        if (!passwordEncoder.matches(request.getPassword(), account.getCustomer().getPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        List<Transaction> transactions = transactionRepository.findByAccount_AccountNumber(account.getAccountNumber());


        return transactions.stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }


    private Account getAccountNumber(String accountNumber) {


        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if (account.isUnregistered()) {
            throw new CustomException(UNREGISTERED_ACCOUNT);
        }

        return account;
    }



}
