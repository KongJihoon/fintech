package com.zerobase.fintech.transaction.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.zerobase.fintech.account.domain.entity.Account;
import com.zerobase.fintech.account.type.Bank;
import com.zerobase.fintech.transaction.type.TransactionType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Account account;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull
    private Long balance;

    private String depositName;

    private String withdrawName;

    private String receivedName;

    private String receivedAccount;

    private Bank receivedBank;

    @CreatedDate
    private LocalDateTime transactionAt;


}
