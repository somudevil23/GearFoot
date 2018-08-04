package com.niit.pistona.DAO;

import java.util.List;

import com.niit.pistona.Model.CustomerOrder;

public interface OrderDAO {

boolean insert(CustomerOrder order);
	
	List<CustomerOrder> viewAllOrder(int cartId);
	
	List<CustomerOrder> viewreceipt(String orderid);
}
