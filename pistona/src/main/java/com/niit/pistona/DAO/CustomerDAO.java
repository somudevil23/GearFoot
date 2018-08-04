package com.niit.pistona.DAO;

import java.util.List;

import com.niit.pistona.Model.Customer;
import com.niit.pistona.Model.UserCredentials;

public interface CustomerDAO {

	public boolean addCustomer(Customer customer);
	public boolean delCustomer(String emailId);
	public Customer showCustomer(String emailId);
	public List<Customer> showAllCustomer();
	public UserCredentials showcred(String email);
	public boolean saveorupdatepassword(UserCredentials uc);
}
