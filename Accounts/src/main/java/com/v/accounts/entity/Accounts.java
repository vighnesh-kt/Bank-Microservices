package com.v.accounts.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {


    private Long customerId;

    @Id
    private Long accountNumber;

    private String accountType;


    private String branchAddress;

}
