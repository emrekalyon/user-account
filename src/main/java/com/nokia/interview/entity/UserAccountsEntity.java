package com.nokia.interview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "user_accounts")
public class UserAccountsEntity {

	@Id
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	@Size(min = 3,max = 150)
	@Column(name = "name")
	private String name;

	@NotBlank
	@Size(min = 9,max = 12)
	@Column(name = "phone")
	private String phone;

	@NotBlank
	@Size(max = 200)
	@Column(name = "email")
	private String email;
	
	@Size(max = 200)
	@Column(name = "address")
	private String address;

	@NotBlank
	@Size(max = 56)
	@Column(name = "country")
	private String country;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "department")
	private String department;

}
