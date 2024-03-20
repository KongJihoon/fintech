package com.zerobase.fintech.account.domain.entity;


import com.zerobase.fintech.account.type.AccountStatus;
import com.zerobase.fintech.account.type.Bank;
import com.zerobase.fintech.global.domain.BaseEntity;
import com.zerobase.fintech.user.domain.entity.Customer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.zerobase.fintech.account.type.AccountStatus.UNREGISTERED;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Bank bank;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @NotNull
    @Column(unique = true)
    private String accountNumber;

    @NotNull
    private String accountName;

    @NotNull
    @Min(value = 0, message = "계좌의 잔액은 0원 이상이어야 합니다.")
    private Long balance;

    private LocalDateTime unRegisteredAt;


    public void increaseBalance(Long balance){
        this.balance += balance;
    }

    public void reductionBalance(Long balance){
        this.balance -= balance;
    }

    public boolean isUnregistered() {
        return this.accountStatus.equals(UNREGISTERED);
    }

}
