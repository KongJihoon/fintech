package com.zerobase.fintech.account.service;

import com.zerobase.fintech.account.dto.AccountDto;
import com.zerobase.fintech.account.dto.CreateAccount;

public interface AccountService {

    AccountDto createAccount(CreateAccount.Request request);


}
