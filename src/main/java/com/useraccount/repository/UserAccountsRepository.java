package com.useraccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useraccount.entity.UserAccountsEntity;

public interface UserAccountsRepository extends JpaRepository<UserAccountsEntity, Long>{

}
