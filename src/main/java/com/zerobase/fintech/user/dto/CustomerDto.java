package com.zerobase.fintech.user.dto;


import com.zerobase.fintech.auth.type.UserType;
import com.zerobase.fintech.user.domain.entity.Customer;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;

    private String userName;

    private UserType userType;

    private String phone;

    private String password;

    public static CustomerDto fromEntity(Customer customer){

        return CustomerDto.builder()
                .id(customer.getId())
                .userName(customer.getUsername())
                .userType(customer.getUserType())
                .phone(customer.getPhone())
                .password(customer.getPassword())
                .build();
    }
}
