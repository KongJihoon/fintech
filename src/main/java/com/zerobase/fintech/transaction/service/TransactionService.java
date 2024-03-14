package com.zerobase.fintech.transaction.service;

import com.zerobase.fintech.transaction.dto.DepositDto;

public interface TransactionService {

    DepositDto.Response deposit(DepositDto.Request request);

}
