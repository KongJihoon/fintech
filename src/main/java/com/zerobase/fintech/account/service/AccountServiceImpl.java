package com.zerobase.fintech.account.service;


import com.zerobase.fintech.account.domain.entity.Account;
import com.zerobase.fintech.account.domain.repository.AccountRepository;
import com.zerobase.fintech.account.dto.AccountDto;
import com.zerobase.fintech.account.dto.CreateAccount;
import com.zerobase.fintech.global.exception.CustomException;
import com.zerobase.fintech.user.domain.entity.Customer;
import com.zerobase.fintech.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.zerobase.fintech.account.type.AccountStatus.IN_USE;
import static com.zerobase.fintech.account.type.Bank.ZERO_BASE;
import static com.zerobase.fintech.global.type.ErrorCode.MAX_ACCOUNT_PER_USER_10;
import static com.zerobase.fintech.global.type.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
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
