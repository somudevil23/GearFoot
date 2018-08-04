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
public class FrontController {

	@Autowired
	CategoryDAO categorydao;
	@Autowired
	ProductDAO productdao;
	@RequestMapping(value = { "/", "/home", "/index" })
	public String index(Model M) {

		M.addAttribute("title", "Home");
		M.addAttribute("userClickHome", true);
		return "page";

	}

	@RequestMapping(value = "about")
	public String about(Model M) {
		M.addAttribute("title ", "About Us");
		M.addAttribute("userClickAbout", true);
		return "page";
	}
	@RequestMapping(value = "contact")
	public String contact(Model M) {

		M.addAttribute("title", "Contact Us");

		
		M.addAttribute("userClickContact", true);
		return "page";

	}
}
