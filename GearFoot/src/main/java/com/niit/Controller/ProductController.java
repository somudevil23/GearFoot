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
public class ProductController {
	
	@Autowired
	CategoryDAO categorydao;
	@Autowired
	ProductDAO productdao;
	
	@RequestMapping(value = "productview")
	public String productsview(Model M) {
		M.addAttribute("title","ProductsView");
		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("userClickProductView",true);
		return "page";
	}
	
	@RequestMapping(value = "info")
	public String productInfo(@RequestParam String prodName, Product product, Model M ) {
		M.addAttribute("msg", false);
		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("product", productdao.showProduct(prodName));
		M.addAttribute("title", "Product Info"); 	
		M.addAttribute("userClickProductInfo", true);
		M.addAttribute("categorylist", categorydao.showAllCategory());
		return "page";
	}
	@RequestMapping(value = "fff/{prodId}")
	public String productInf(@RequestParam int prodId, Product product, Model M ) {
		M.addAttribute("msg", false);
		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("product", productdao.getProduct(prodId));
		M.addAttribute("title", "Product Info"); 	
		M.addAttribute("userClickProductInfo", true);
		M.addAttribute("categorylist", categorydao.showAllCategory());
		return "page";
	}
	@RequestMapping(value = "products")
	public String product(Model M) {

		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("catlist", categorydao.showAllCategory());
		M.addAttribute("status", false);
		M.addAttribute("product", new Product());
		M.addAttribute("title", "Product");
		M.addAttribute("userClickProduct", true);
		M.addAttribute("edit", false);
		return "page";

	}

	@RequestMapping(value = "setproduct")
	public String setpro(@Valid @ModelAttribute("product") Product product,BindingResult result, Model M) {
		System.out.println("print me");
		if(result.hasErrors()) 
		{
			System.out.println("inside error");
			System.out.println(result.getErrorCount());
			M.addAttribute("productlist", productdao.showallProduct());
			M.addAttribute("catlist",categorydao.showAllCategory());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("product", product);
			M.addAttribute("title", "Product");
			M.addAttribute("userClickProduct", true);
			return "page";
		}
		
		try {
			System.out.println("inside try");

			productdao.addProduct(product);
	     	uploadfile(product.getProdId(),product.getPimage());
			return "redirect:/products";
		} catch (Exception e) {
			System.out.println("inside catch"+e.getMessage());

			M.addAttribute("productlist", productdao.showallProduct());
			M.addAttribute("catlist",categorydao.showAllCategory());
			M.addAttribute("status", true);
			M.addAttribute("edit", false);
			M.addAttribute("product", product);
			M.addAttribute("title", "Product");
			M.addAttribute("userClickProduct", true);
			return "page";
		}
	}
	@RequestMapping(value = "delprod/{prodName}")
	public String delprod(@PathVariable String prodName, Model M) {
		try {
			Product p=productdao.showProduct(prodName);
			productdao.deleteProduct(prodName);
			String path="E:\\Somu\\GearFoot\\src\\main\\webapp\\resources\\pimages\\";
			//E:\Somu\GearFoot\src\main\webapp\resources
			path=path+String.valueOf(p.getProdId()+".jpg");
			File f1=new File(path);
			f1.delete();
			Thread.sleep(10000);
			return "redirect:/products";
		} catch (Exception e) {
			M.addAttribute("productlist", productdao.showallProduct());
			M.addAttribute("catlist",categorydao.showAllCategory());
			M.addAttribute("status", false);
			M.addAttribute("product", new Product());
			M.addAttribute("title", "Product");
			M.addAttribute("userClickProduct", true);
			M.addAttribute("edit", false);
			return "page";
		}
	}
	@RequestMapping(value = "editprod/{prodName}")
	public String editProduct(@PathVariable String prodName, Product product, Model M ) {
		
		M.addAttribute("productlist", productdao.showallProduct());
		M.addAttribute("status", false);
		M.addAttribute("edit", true);
		M.addAttribute("product", productdao.showProduct(prodName));
		M.addAttribute("title", "Edit Product");
		M.addAttribute("userClickProduct", true);
		M.addAttribute("catlist",categorydao.showAllCategory());
		return "page";	
	}
	void uploadfile(int productId,MultipartFile f) throws Exception
	{
		String path="E:\\Somu\\GearFoot\\src\\main\\webapp\\resources\\pimages\\";
		path=path+String.valueOf(productId+".jpg");
		if(!f.isEmpty())
		{
			byte[] b=f.getBytes();
			System.out.println(b);
			BufferedOutputStream bs=new  BufferedOutputStream(new FileOutputStream(new File(path)));
			bs.write(b);
			bs.close();
			Thread.sleep(10000);
		}
	}
}
