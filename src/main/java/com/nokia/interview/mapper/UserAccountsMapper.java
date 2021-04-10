package com.nokia.interview.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nokia.interview.domain.UserAccounts;
import com.nokia.interview.domain.request.UserAccountsCreateRequest;
import com.nokia.interview.entity.UserAccountsEntity;

@Mapper(componentModel = "spring")
public interface UserAccountsMapper {

	UserAccountsEntity toEntity(UserAccountsCreateRequest userAccountsCreateRequest);

	UserAccounts toDomain(UserAccountsEntity entity);

	List<UserAccounts> toDomainList(List<UserAccountsEntity> findAll);

}
