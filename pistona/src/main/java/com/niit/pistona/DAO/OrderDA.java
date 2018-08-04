package com.niit.pistona.DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.pistona.Model.CustomerOrder;
@Repository("orderDAO")
@Transactional
public class OrderDA implements OrderDAO {
	@Autowired
	SessionFactory sessionFactory;

	private static List<CustomerOrder> orderlist = new ArrayList<>();

	CustomerOrder order = new CustomerOrder();

	@Override
	public boolean insert(CustomerOrder order) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(order);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CustomerOrder> viewAllOrder(int cartId) {
		try {
			orderlist = (List<CustomerOrder>) sessionFactory.getCurrentSession()
					.createQuery("from CustomerOrder where cartId=" + cartId).list();
			return orderlist;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<CustomerOrder> viewreceipt(String orderid) {
		try {
			orderlist = (List<CustomerOrder>) sessionFactory.getCurrentSession()
					.createQuery("from CustomerOrder where orderId='"+ orderid+"'").list();
			return orderlist;
		} catch (Exception e) {
			return null;
		}
	}
}
