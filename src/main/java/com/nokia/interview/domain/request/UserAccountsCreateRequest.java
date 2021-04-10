package com.nokia.interview.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserAccountsCreateRequest {
	
	private Long id;

	@NotBlank
	@Size(min = 3,max = 150)
	private String name;

	@NotBlank
	@Size(min = 9,max = 12)
	private String phone;
	
	@Email
	@NotBlank
	@Size(max = 200)
	private String email;
	
	@Size(max = 200)
	private String address;

	@NotBlank
	@Size(max = 56)
	private String country;
	
	@NotBlank
	@Size(max = 50)
	private String department;
}
