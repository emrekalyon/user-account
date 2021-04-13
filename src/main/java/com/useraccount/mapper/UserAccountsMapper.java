package com.useraccount.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.useraccount.domain.UserAccounts;
import com.useraccount.domain.request.UserAccountsCreateRequest;
import com.useraccount.entity.UserAccountsEntity;

@Mapper(componentModel = "spring")
public interface UserAccountsMapper {

	UserAccountsEntity toEntity(UserAccountsCreateRequest userAccountsCreateRequest);

	UserAccounts toDomain(UserAccountsEntity entity);

	List<UserAccounts> toDomainList(List<UserAccountsEntity> findAll);

}
