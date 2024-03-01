package com.zerobase.fintech.account.service;

import com.zerobase.fintech.account.dto.AccountDto;
import com.zerobase.fintech.account.dto.CreateAccount;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(CreateAccount.Request request);

    List<AccountDto> getAccountByUserId(Long userId);

    AccountDto deleteAccount(Long userId, String accountNumber);


}
