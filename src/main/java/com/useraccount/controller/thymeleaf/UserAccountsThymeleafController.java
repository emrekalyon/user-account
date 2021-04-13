package com.useraccount.controller.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.useraccount.domain.UserAccounts;
import com.useraccount.domain.request.UserAccountsCreateRequest;
import com.useraccount.service.UserAccountsService;

@Controller
@RequestMapping("/tyhmeleaf/user-accounts")
public class UserAccountsThymeleafController {

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
		
		boolean exists = userAccountsService.existsById( userAccountsCreateRequest.getId() );
		
		userAccountsService.createUserAccounts(userAccountsCreateRequest);
		if(!exists) {
			model.addAttribute("successMessage", "User Accounts Created Successfully");
		}else {
			model.addAttribute("successMessage", "User Accounts Updated Successfully");
		}
		
		model.addAttribute("userAccounts", userAccountsService.findAll());
		model.addAttribute("userAccount", new UserAccounts());
		model.addAttribute("visibleForm", false);
		return "home.html";
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
		model.addAttribute("successMessage", "User Accounts Deleted Successfully");
		return "home.html";
	}

	@GetMapping(value = "/add-accounts")
	public String addStudent(final Model model) {
		model.addAttribute("userAccounts", userAccountsService.findAll());
		model.addAttribute("userAccount", new UserAccounts());
		model.addAttribute("visibleForm", true);
		return "home.html";
	}
}
