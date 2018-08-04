package com.niit.pistona;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.niit.pistona.DAO.CategoryDAO;
import com.niit.pistona.Model.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =Config.class)
public class TestCatDAO {
	@Autowired
	CategoryDAO categorydao;
	 Category category = new Category();
	
	@Before
	public void setUp() throws Exception {
		
		category.setCategoryName("Jeans");
		category.setCategoryDescription("Jeans Description");
	}

	@After
	public void tearDown() {
		categorydao.deleteCategory(category.getCategoryName());
	}
	
    @Ignore
	@Test
	public void test() 
	{
		categorydao.addCategory(category);
		
		List <Category> category = categorydao.showAllCategory();
		assertTrue("success", category.size()>0);
	}

}
