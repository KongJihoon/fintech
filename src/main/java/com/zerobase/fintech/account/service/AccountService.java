package com.zerobase.fintech.account.service;

import com.zerobase.fintech.account.dto.AccountDto;
import com.zerobase.fintech.account.dto.CreateAccount;
import com.zerobase.fintech.account.dto.OtherBankAccountCreate;
import com.zerobase.fintech.account.dto.SearchAccount;

import java.util.List;

public interface AccountService {

    // 계좌 생생
    AccountDto createAccount(CreateAccount.Request request);

    // 계좌 조회
    List<AccountDto> getAccountByUserId(Long userId);

    List<AccountDto> getAccountByCustomer(SearchAccount.Request request);

    // 계좌 삭제
    AccountDto deleteAccount(Long userId, String accountNumber);

    // 타 은행 계좌 생성
    OtherBankAccountCreate.Response registerOtherBankAccount(OtherBankAccountCreate.Request request);



}
