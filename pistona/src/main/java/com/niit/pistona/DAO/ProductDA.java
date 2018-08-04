package com.niit.pistona.DAO;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.pistona.Model.Category;
import com.niit.pistona.Model.Product;


@Repository("productDAO)")
@Transactional
public class ProductDA implements ProductDAO {
	@Autowired
	SessionFactory sf;

	@Override
	public boolean addProduct(Product product) {
		try {
			sf.getCurrentSession().saveOrUpdate(product);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}
	}

	
	@Override
	public boolean deleteProduct(String productName) {
		try {
			sf.getCurrentSession().delete(showProduct(productName));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	@Override
	public Product showProduct(String productName) {
		try {
			Product product=(Product)sf.getCurrentSession().createQuery("from Product where prodName='"+productName+"'").uniqueResult();
			return product;
		}

		catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Product> showallProduct() {
		try {

			ArrayList<Product> product = (ArrayList<Product>) sf.getCurrentSession().createQuery("from Product").list();
			System.out.println(product.isEmpty());
			return product;
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return null;
		}
	}

	@Override
	public List<Product> searchCategory(String categoryName) 
	{
		try
		{
			Category category=(Category)sf.getCurrentSession().createQuery("from Category where categoryName = '"+categoryName+"'").uniqueResult();
			List<Product> product= (List<Product>) sf.getCurrentSession().createQuery("from Product where categoryId="+category.getCategoryId());
			return product;
		}
			catch (Exception e)
			{
				return null;
			}
		}


	@Override
	public Product getProduct(int id) {
		try {
			Product product=(Product)sf.getCurrentSession().createQuery("from Product where prodId='"+id+"'").uniqueResult();
			return product;
		}

		catch (Exception e) {
			return null;
		}
	}


	@Override
	public Product saveOrUpdateProduct(Product product) {
		Session session=sf.getCurrentSession();
	    session.saveOrUpdate(product);
	    return product;
	}
}
