package com.zerobase.fintech.user.domain.repository;

import com.zerobase.fintech.user.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByPhone(String phone);

    Optional<Customer> findByPhone(String phone);

}
