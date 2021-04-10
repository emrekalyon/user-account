package com.nokia.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nokia.interview.entity.UserAccountsEntity;

public interface UserAccountsRepository extends JpaRepository<UserAccountsEntity, Long>{

}
