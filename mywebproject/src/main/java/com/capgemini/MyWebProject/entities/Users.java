package com.capgemini.MyWebProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Password is mandatory")
	private String password;

	@NotBlank(message = "User Type is mandatory")
	private String usertype;

	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String phone;

	public Users() {
		super();
	}

	public Users(Long id, String name, String email, String password, String usertype, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", usertype="
				+ usertype + ", phone=" + phone + "]";
	}
}

