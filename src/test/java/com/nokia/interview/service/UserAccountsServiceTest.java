package com.nokia.interview.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;

import com.nokia.interview.domain.request.UserAccountsCreateRequest;

@ExtendWith(SpringExtension.class)
@ActiveProfiles({"test"})
@SpringBootTest
public class UserAccountsServiceTest {

	@Autowired
	private UserAccountsService userAccountService;
	
	
	@Test
	public void shouldCreateUserAccounts() {
		
		final UserAccountsCreateRequest userAccountsCreateRequest = new UserAccountsCreateRequest();
		userAccountsCreateRequest.setId(RandomUtils.nextLong());
		userAccountsCreateRequest.setName(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(3, 150)));
		userAccountsCreateRequest.setPhone(RandomStringUtils.randomNumeric(RandomUtils.nextInt(9, 12)));
		userAccountsCreateRequest.setEmail(RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(0, 190))+"@gmail.com");
		userAccountsCreateRequest.setAddress(RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(0, 150)));
		userAccountsCreateRequest.setCountry(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(0, 56)));
		userAccountsCreateRequest.setDepartment(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(0, 50)));
		
		userAccountService.createUserAccounts(userAccountsCreateRequest);
	}
	
	@Test
	public void shouldGetErrorWhenPhoneIsEmpty() {
		
		final UserAccountsCreateRequest userAccountsCreateRequest = new UserAccountsCreateRequest();
		userAccountsCreateRequest.setId(RandomUtils.nextLong());
		userAccountsCreateRequest.setName(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(3, 150)));
		userAccountsCreateRequest.setPhone("");
		userAccountsCreateRequest.setEmail(RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(0, 190))+"@gmail.com");
		userAccountsCreateRequest.setAddress(RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(0, 150)));
		userAccountsCreateRequest.setCountry(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(0, 56)));
		userAccountsCreateRequest.setDepartment(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(0, 50)));
		
		final TransactionSystemException ex = assertThrows(TransactionSystemException.class, () -> {
			 userAccountService.createUserAccounts(userAccountsCreateRequest);
		});
		
		final ConstraintViolationException exp = (ConstraintViolationException) ex.getCause().getCause();
		
		assertEquals(2, exp.getConstraintViolations().size() );
		ConstraintViolation<?> exceptionDetail = exp.getConstraintViolations().iterator().next();
		assertEquals("phone", exceptionDetail.getPropertyPath().iterator().next().getName());
		
	}
	
	
	@Test
	public void shouldGetErrorWhenExceedMaxNameLength() {
		
		final UserAccountsCreateRequest userAccountsCreateRequest = new UserAccountsCreateRequest();
		userAccountsCreateRequest.setId(RandomUtils.nextLong());
		userAccountsCreateRequest.setName(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(151,500)));
		userAccountsCreateRequest.setPhone(RandomStringUtils.randomNumeric(RandomUtils.nextInt(9, 12)));
		userAccountsCreateRequest.setEmail(RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(0, 190))+"@gmail.com");
		userAccountsCreateRequest.setAddress(RandomStringUtils.randomAlphanumeric(RandomUtils.nextInt(0, 150)));
		userAccountsCreateRequest.setCountry(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(0, 56)));
		userAccountsCreateRequest.setDepartment(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(0, 50)));
		
		final TransactionSystemException ex = assertThrows(TransactionSystemException.class, () -> {
			 userAccountService.createUserAccounts(userAccountsCreateRequest);
		});
		
		final ConstraintViolationException exp = (ConstraintViolationException) ex.getCause().getCause();
		
		assertEquals(1, exp.getConstraintViolations().size() );
		ConstraintViolation<?> exceptionDetail = exp.getConstraintViolations().iterator().next();
		assertEquals("name", exceptionDetail.getPropertyPath().iterator().next().getName());
		
	}
}
