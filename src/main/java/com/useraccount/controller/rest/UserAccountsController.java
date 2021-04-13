package com.useraccount.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useraccount.domain.UserAccounts;
import com.useraccount.domain.request.UserAccountsCreateRequest;
import com.useraccount.service.UserAccountsService;

@RestController
@RequestMapping("/user-accounts")
public class UserAccountsController {

	@Autowired
	private UserAccountsService userAccountsService;
	
	@PostMapping
	public ResponseEntity<?> createUserAccounts(@RequestBody @Valid final UserAccountsCreateRequest userAccountsCreateRequest) {
		userAccountsService.createUserAccounts(userAccountsCreateRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("User Accounts Created Successfully");
	}

	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateUserAccounts(@PathVariable("id") final Long userId,
			@RequestBody @Valid final UserAccountsCreateRequest userAccountsCreateRequest) {
		userAccountsService.updateUserAccounts(userId, userAccountsCreateRequest);
		return ResponseEntity.status(HttpStatus.OK).body("User Accounts Updated Successfully");
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<UserAccounts> getUserAccounts(@PathVariable("id") final Long userId,
			final Model model) {

		final UserAccounts userAccounts = userAccountsService.findById(userId);
		return ResponseEntity.ok(userAccounts);
	}

	@GetMapping("/delete/id/{id}")
	public ResponseEntity<?> deleteUserAccounts(@PathVariable("id") final Long userId,
			final Model model) {
		userAccountsService.deleteById(userId);
		return ResponseEntity.status(HttpStatus.OK).body("User Accounts Deleted Successfully");
	}

	@GetMapping
	public ResponseEntity<List<UserAccounts>> getAllUserAccounts() {

		final List<UserAccounts> userAccountsList = userAccountsService.findAll();

		return ResponseEntity.ok(userAccountsList);
	}
}
