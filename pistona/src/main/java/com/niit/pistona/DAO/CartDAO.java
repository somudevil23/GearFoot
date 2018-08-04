package com.niit.pistona.DAO;

import com.niit.pistona.Model.Cart;
import java.util.List;
public interface CartDAO {

	boolean add(Cart cart);
	boolean delete(int id);
	Cart get(int id);
	List<Cart> show(int cartId);
}
