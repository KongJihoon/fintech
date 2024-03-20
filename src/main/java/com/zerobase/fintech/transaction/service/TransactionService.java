package com.zerobase.fintech.transaction.service;

import com.zerobase.fintech.transaction.dto.*;

import java.util.List;

public interface TransactionService {

    TransactionDto deposit(DepositDto.Request request);


    TransactionDto withdraw(WithDrawDto.Request request);

    TransactionDto remittance(RemittanceDto.Request request);

    List<TransactionDto> getTransaction(TransactionListDto.Request request);

}
