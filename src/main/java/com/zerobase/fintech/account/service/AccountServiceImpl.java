package com.zerobase.fintech.account.service;


import com.zerobase.fintech.account.domain.entity.Account;
import com.zerobase.fintech.account.domain.repository.AccountRepository;
import com.zerobase.fintech.account.dto.AccountDto;
import com.zerobase.fintech.account.dto.CreateAccount;
import com.zerobase.fintech.account.dto.OtherBankAccountCreate;
import com.zerobase.fintech.account.type.AccountStatus;
import com.zerobase.fintech.account.type.Bank;
import com.zerobase.fintech.global.exception.CustomException;
import com.zerobase.fintech.user.domain.entity.Customer;
import com.zerobase.fintech.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zerobase.fintech.account.type.AccountStatus.IN_USE;
import static com.zerobase.fintech.account.type.AccountStatus.UNREGISTERED;
import static com.zerobase.fintech.account.type.Bank.ZERO_BASE;
import static com.zerobase.fintech.global.type.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public AccountDto createAccount(CreateAccount.Request request) {
        Customer customer = getCustomer(request.getUserId());

        validateCreateAccount(customer);

        String newAccountNumber = makeAccountNumber();

        return AccountDto.fromEntity(
                accountRepository.save(Account.builder()
                        .customer(customer)
                        .bank(ZERO_BASE)
                        .accountStatus(IN_USE)
                        .accountNumber(newAccountNumber)
                        .accountName(request.getAccountName())
                        .balance(request.getInitialBalance())
                        .build()));
    }

    @Override
    @Transactional
    public List<AccountDto> getAccountByUserId(Long userId) {

        Customer customer = getCustomer(userId);

        List<Account> accounts = accountRepository.findByCustomer(customer);


        return accounts.stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccountDto deleteAccount(Long userId, String accountNumber) {

        Customer customer = getCustomer(userId);

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomException(ACCOUNT_NOT_FOUND));

        validateDeleteAccount(customer, account);

        account.setAccountStatus(UNREGISTERED);
        account.setUnRegisteredAt(LocalDateTime.now());

        accountRepository.save(account);

        return AccountDto.fromEntity(account);
    }

    @Override
    @Transactional
    public OtherBankAccountCreate.Response registerOtherBankAccount(OtherBankAccountCreate.Request request) {
        Customer customer = getCustomer(request.getUserId());

        validateCreateAccount(customer);

        if (ZERO_BASE.equals(request.getBank())) {
           throw new CustomException(ONLY_REGISTERED_OTHER_BANK);
        }

        boolean present = accountRepository.findByAccountNumber(request.getAccountNumber()).isPresent();

        if (present) {
            throw new CustomException(ALREADY_EXIST_ACCOUNT_NUMBER);
        }

        AccountDto accountDto = AccountDto.fromEntity(
                accountRepository.save(Account.builder()
                        .customer(customer)
                        .bank(request.getBank())
                        .accountStatus(IN_USE)
                        .accountNumber(request.getAccountNumber())
                        .accountName(request.getAccountName())
                        .balance(request.getInitialBalance())
                        .build()));

        return OtherBankAccountCreate.Response.from(accountDto);
    }

    private void validateDeleteAccount(Customer customer, Account account) {

        if (!Objects.equals(customer.getId(), account.getCustomer().getId())) {
            throw new CustomException(USER_ACCOUNT_UN_MATCH);
        }
        if (account.getAccountStatus() == AccountStatus.UNREGISTERED) {
            throw new CustomException(ACCOUNT_ALREADY_UNREGISTERED);
        }
        if (account.getBalance() > 0) {
            throw new CustomException(BALANCE_NOT_EMPTY);
        }
    }


    private String makeAccountNumber(){
        return accountRepository.findFirstByOrderByIdDesc()
                .map(account -> (Long.parseLong(account.getAccountNumber()) + 1) + "")
                .orElse("33300000000");
    }
    private Customer getCustomer(Long userId){

        return customerRepository.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

    }

    private void validateCreateAccount(Customer customer){
        if(accountRepository.countByCustomer(customer) >= 10){
            throw new CustomException(MAX_ACCOUNT_PER_USER_10);
        }
    }



}
