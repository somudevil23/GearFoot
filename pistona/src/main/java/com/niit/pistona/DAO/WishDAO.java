package com.niit.pistona.DAO;

import java.util.List;

import com.niit.pistona.Model.Wish;

public interface WishDAO {
	boolean add(Wish wish);
	boolean delete(int id);
	Wish get(int id);
	List<Wish> show(int cartId);
}
