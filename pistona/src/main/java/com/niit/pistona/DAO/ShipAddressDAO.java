package com.niit.pistona.DAO;

import java.util.List;

import com.niit.pistona.Model.ShipAddress;

public interface ShipAddressDAO {

	boolean add(ShipAddress shipAddress);
	boolean delete(int id);
	ShipAddress show(int id);
	List<ShipAddress> list(int cartId);
}
