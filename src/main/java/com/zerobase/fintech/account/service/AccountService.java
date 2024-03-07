package com.zerobase.fintech.account.service;

import com.zerobase.fintech.account.dto.AccountDto;
import com.zerobase.fintech.account.dto.CreateAccount;
import com.zerobase.fintech.account.dto.OtherBankAccountCreate;

import java.util.List;

public interface AccountService {

    // 계좌 생생
    AccountDto createAccount(CreateAccount.Request request);

    // 계좌 조회
    List<AccountDto> getAccountByUserId(Long userId);

    // 계좌 삭제
    AccountDto deleteAccount(Long userId, String accountNumber);

    // 타 은행 계좌 생성
    OtherBankAccountCreate.Response registerOtherBankAccount(OtherBankAccountCreate.Request request);



}
