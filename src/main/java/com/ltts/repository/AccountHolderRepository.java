package com.ltts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ltts.model.AccountHolder;




@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

}
