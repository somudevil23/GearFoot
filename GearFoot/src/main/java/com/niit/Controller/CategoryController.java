package com.niit.Controller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.pistona.DAO.CategoryDAO;
import com.niit.pistona.DAO.ProductDAO;
import com.niit.pistona.Model.Category;
import com.niit.pistona.Model.Product;

@Controller
public class CategoryController {

	@Autowired
	CategoryDAO categorydao;
	@Autowired
	ProductDAO productdao;

	@RequestMapping(value = "category")
	public String category(Model M) {

		M.addAttribute("categorylist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("edit", false);
		M.addAttribute("category", new Category());
		M.addAttribute("title", "Category");
		M.addAttribute("userClickCategory", true);
		return "page";

	}

	@RequestMapping(value = "setcategory")
	public String setcat(@Valid @ModelAttribute("category") Category category, BindingResult result, Model M) {
		//System.out.println("print me");
		if (result.hasErrors()) {
			M.addAttribute("categorylist", categorydao.showAllCategory());
			M.addAttribute("status", "true");
			M.addAttribute("edit", false);
			M.addAttribute("category", category);
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "page";

		} else {
			try {
				categorydao.addCategory(category);
				return "redirect:/category";
			} catch (Exception e) {
				M.addAttribute("categorylist", categorydao.showAllCategory());
				M.addAttribute("status", "true");
				M.addAttribute("edit", false);
				M.addAttribute("category", new Category());
				M.addAttribute("title", "Category");
				M.addAttribute("userClickCategory", true);
				return "page";
			}
		}

	}

	@RequestMapping(value = "delcat")
	public String delcat(@RequestParam(name = "cname") String categoryName, Model M) {
		try {
			categorydao.deleteCategory(categoryName);
			return "redirect:/category";
		} catch (Exception e) {
			M.addAttribute("categorylist", categorydao.showAllCategory());
			M.addAttribute("status", "true");
			M.addAttribute("edit", false);
			M.addAttribute("category", new Category());
			M.addAttribute("title", "Category");
			M.addAttribute("userClickCategory", true);
			return "page";
		}
	}

	@RequestMapping(value = "editcat")
	public String showcategory(@RequestParam(name = "cname") String categoryName, Model M) {

		M.addAttribute("categorylist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("edit", true);
		M.addAttribute("category", categorydao.showCategory(categoryName));
		M.addAttribute("title", "Category");
		M.addAttribute("userClickCategory", true);
		return "page";

	}
	
	
}
