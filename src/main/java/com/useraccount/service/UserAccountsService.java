package com.useraccount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useraccount.domain.UserAccounts;
import com.useraccount.domain.request.UserAccountsCreateRequest;
import com.useraccount.entity.UserAccountsEntity;
import com.useraccount.exception.UserNotFoundException;
import com.useraccount.mapper.UserAccountsMapper;
import com.useraccount.repository.UserAccountsRepository;

@Service
public class UserAccountsService {

	@Autowired
	private final UserAccountsMapper userAccountsMapper;
	
	@Autowired
	private final UserAccountsRepository userAccountsRepository;
	
	
	@Autowired
	public UserAccountsService(final UserAccountsMapper userAccountsMapper,final UserAccountsRepository userAccountsRepository) {
		this.userAccountsMapper = userAccountsMapper;
		this.userAccountsRepository = userAccountsRepository;
	}


	public void createUserAccounts(final UserAccountsCreateRequest userAccountsCreateRequest) {
		
		final UserAccountsEntity userAccounts = userAccountsMapper.toEntity(userAccountsCreateRequest);
		userAccountsRepository.save(userAccounts);
	}


	public void updateUserAccounts(final Long userId,final UserAccountsCreateRequest userAccountsCreateRequest) {
		
		final boolean isExists = userAccountsRepository.existsById(userId);
		
		if(!isExists) {
			throw new UserNotFoundException(userId);
		}
		
		final UserAccountsEntity userAccountsEntity = userAccountsMapper.toEntity(userAccountsCreateRequest);
		userAccountsEntity.setId(userId);
		userAccountsRepository.save(userAccountsEntity);
	}


	public UserAccounts findById(final Long userId) {
		final UserAccountsEntity entity = userAccountsRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		
		return userAccountsMapper.toDomain(entity);
	}


	public void deleteById(final Long userId) {
		
		userAccountsRepository.deleteById(userId);
		
	}


	public List<UserAccounts> findAll() {
		return userAccountsMapper.toDomainList(userAccountsRepository.findAll());
	}


	public boolean existsById(final Long id) {
		return userAccountsRepository.existsById(id);
	}
	

	
}
