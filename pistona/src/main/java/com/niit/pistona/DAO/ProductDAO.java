package com.niit.pistona.DAO;

import java.util.List;

import com.niit.pistona.Model.Product;

public interface ProductDAO {
	public boolean addProduct(Product product);
	 public boolean deleteProduct(String productName);
	 public Product showProduct(String productName);
	 public List<Product> showallProduct();
	 public List<Product> searchCategory(String categoryName);
	public Product getProduct(int id);
	public Product saveOrUpdateProduct(Product product);
}
