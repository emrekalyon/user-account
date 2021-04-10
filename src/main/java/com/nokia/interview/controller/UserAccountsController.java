package com.nokia.interview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nokia.interview.domain.UserAccounts;
import com.nokia.interview.domain.request.UserAccountsCreateRequest;
import com.nokia.interview.service.UserAccountsService;

@Controller
@RequestMapping("/user-accounts")
public class UserAccountsController {

	@Autowired
	private UserAccountsService userAccountsService;

	@PostMapping
	public String createUserAccounts(
			@ModelAttribute("userAccount") @Valid final UserAccountsCreateRequest userAccountsCreateRequest,
			final BindingResult errors,
			final Model model) {
		
		if(errors.hasErrors()) {
			List<String> errorMessages = new ArrayList<>();
			for (FieldError fieldError : errors.getFieldErrors()) {
				errorMessages.add(fieldError.getField()+ " " + fieldError.getDefaultMessage());
			}
			model.addAttribute("errorMessages", errorMessages);
			model.addAttribute("userAccounts", userAccountsService.findAll());
			model.addAttribute("visibleForm", true);
			return "home.html";
		}
		
		userAccountsService.createUserAccounts(userAccountsCreateRequest);
		if(userAccountsCreateRequest.getId() == null) {
			model.addAttribute("successMessage", "User Accounts Created Successfully");
		}else {
			model.addAttribute("successMessage", "User Accounts Updated Successfully");
		}
		
		model.addAttribute("userAccounts", userAccountsService.findAll());
		model.addAttribute("userAccount", new UserAccounts());
		model.addAttribute("visibleForm", false);
		return "home.html";
	}

	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateUserAccounts(@PathVariable("id") final Long userId,
			@RequestBody @Valid final UserAccountsCreateRequest userAccountsCreateRequest) {

		userAccountsService.updateUserAccounts(userId, userAccountsCreateRequest);

		return ResponseEntity.status(HttpStatus.OK).body("User Accounts Updated Successfully");
	}

	@GetMapping("/id/{id}")
	public String getUserAccounts(@PathVariable("id") final Long userId,
			final Model model) {

		final UserAccounts userAccounts = userAccountsService.findById(userId);

		model.addAttribute("userAccounts", userAccountsService.findAll());
		model.addAttribute("userAccount", userAccounts);
		model.addAttribute("visibleForm", true);
		
		return "home.html";
	}

	@GetMapping("/delete/id/{id}")
	public String deleteUserAccounts(@PathVariable("id") final Long userId,
			final Model model) {

		userAccountsService.deleteById(userId);

		model.addAttribute("userAccounts", userAccountsService.findAll());
		model.addAttribute("selectedUserAccount", new UserAccounts());
		return "home.html";
	}

	@GetMapping
	public ResponseEntity<List<UserAccounts>> getAllUserAccounts() {

		final List<UserAccounts> userAccountsList = userAccountsService.findAll();

		return ResponseEntity.ok(userAccountsList);
	}

	@GetMapping(value = "/add-accounts")
	public String addStudent(final Model model) {
		model.addAttribute("userAccounts", userAccountsService.findAll());
		model.addAttribute("userAccount", new UserAccounts());
		model.addAttribute("visibleForm", true);
		return "home.html";
	}

	@GetMapping(value = "/update-accounts")
	public String updateUserAccounts(final Model model) {
		model.addAttribute("userAccounts", new UserAccounts());
		return "updateUserAccounts.html";
	}

	@GetMapping(value = "/delete-accounts")
	public String deleteUserAccounts(final Model model) {
		model.addAttribute("userAccounts", userAccountsService.findAll());
		model.addAttribute("selectedUserAccount", new UserAccounts());
		return "deleteUserAccounts.html";
	}

	@GetMapping(value = "/find-accounts")
	public String findUserAccounts(final Model model) {
		model.addAttribute("userAccounts", userAccountsService.findAll() );
		return "findUserAccounts.html";
	}

	@GetMapping(value = "/list-accounts")
	public String listUserAccounts(final Model model) {
		model.addAttribute("userAccounts", new UserAccounts());
		return "listUserAccounts.html";
	}
}
