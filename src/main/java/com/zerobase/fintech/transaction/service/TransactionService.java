package com.zerobase.fintech.transaction.service;

import com.zerobase.fintech.transaction.dto.DepositDto;
import com.zerobase.fintech.transaction.dto.WithDrawDto;

public interface TransactionService {

    DepositDto.Response deposit(DepositDto.Request request);


    WithDrawDto.Response withdraw(WithDrawDto.Request request);

}
