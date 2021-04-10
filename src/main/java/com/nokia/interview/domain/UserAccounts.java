package com.nokia.interview.domain;

import lombok.Data;

@Data
public class UserAccounts{
	
	private Long id;
	
	private String name;

	private String phone;
	
	private String email;
	
	private String address;

	private String country;
	
	private String department;
}
