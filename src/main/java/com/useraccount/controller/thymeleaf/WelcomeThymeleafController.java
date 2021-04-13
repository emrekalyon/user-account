package com.useraccount.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.useraccount.domain.UserAccounts;
import com.useraccount.service.UserAccountsService;


@Controller
public class WelcomeThymeleafController {

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