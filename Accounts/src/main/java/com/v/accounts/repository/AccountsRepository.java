package com.v.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v.accounts.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long>{
	
	Optional<Accounts> findByCustomerId(Long customerId);

	void deleteByCustomerId(Long customerId);

}
