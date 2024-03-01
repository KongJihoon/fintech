package com.zerobase.fintech.account.domain.repository;

import com.zerobase.fintech.account.domain.entity.Account;
import com.zerobase.fintech.user.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findFirstByOrderByIdDesc();

    Integer countByCustomer(Customer customer);

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByCustomer(Customer customer);

}
