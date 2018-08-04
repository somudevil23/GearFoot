package com.niit.pistona.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	
	@NotEmpty(message = "email cannot be blank")
	private String name;
	
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "email cannot be blank")
	@Email(regexp="[a-z0-9_]+@[a-z]+\\.[a-z]{2,3}",message="please enter a valid email id")
	private String EmailId;
	
	@Transient
	@NotEmpty(message = "password cannot be blank")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters")
	private String passsword;
	
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "phone number cannot be blank")
	@Size(min = 10, max = 10)
	@Pattern(regexp="[9876][0-9]{9}", message="must start with either 9/8/7/6 and have 10 numbers")
	private String phoneNumber;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
