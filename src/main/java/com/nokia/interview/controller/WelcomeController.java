package com.nokia.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nokia.interview.domain.UserAccounts;
import com.nokia.interview.service.UserAccountsService;


@Controller
public class WelcomeController {

	@Autowired
	private UserAccountsService userAccountService;
	
    @GetMapping("/")
    public String main(Model model) {
    	final List<UserAccounts> findAll = userAccountService.findAll();
		model.addAttribute("userAccounts", findAll);
		model.addAttribute("userAccount", new UserAccounts());
		model.addAttribute("visibleForm", false);
        return "home.html";
    }

}