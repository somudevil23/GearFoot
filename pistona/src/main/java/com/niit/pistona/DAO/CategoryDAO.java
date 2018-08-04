package com.niit.pistona.DAO;

import java.util.List;

import com.niit.pistona.Model.Category;

public interface CategoryDAO {

	public boolean addCategory(Category category);
	public List<Category> showAllCategory();
	public boolean deleteCategory(String categoryName);
	public Category showCategory(String categoryName);
	
}
