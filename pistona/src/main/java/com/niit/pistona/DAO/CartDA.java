package com.niit.pistona.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.pistona.Model.Cart;
@Repository("cartDAO")
@Transactional
public class CartDA implements CartDAO {

	
	@Autowired
	SessionFactory sessionFactory;
	
	private static List<Cart> cartitems = new ArrayList<>();
	
	Cart cart= new Cart();
	
	@Override
	public boolean add(Cart cart) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(cart);
		
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();

			return false;	
		}

	}

	@Override
	public boolean delete(int id) {
		try {
			
			cart = sessionFactory.getCurrentSession().get(Cart.class, id);
			sessionFactory.getCurrentSession().delete(cart);
		
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();

			return false;	
		}

	}

	@Override
	public List<Cart> show(int cartId) {
		try {
			cartitems = (List<Cart>) sessionFactory.getCurrentSession().createQuery("from Cart where cartId="+cartId).list();
			return cartitems;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Cart get(int id) {
		cart = sessionFactory.getCurrentSession().get(Cart.class, id);
		return cart;
	}
}
