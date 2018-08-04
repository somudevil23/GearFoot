package com.niit.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.niit.pistona.DAO.ProductDAO;
import com.niit.pistona.DAO.WishDAO;
import com.niit.pistona.Model.Product;
import com.niit.pistona.Model.Wish;

@Controller
public class WishListController {
	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private WishDAO wishDAO;
	@RequestMapping(value = "cart/addwish/{prodId}")
	public String addwish(@PathVariable int prodId, Model model, HttpSession session) 
	{
		ArrayList<Wish> wishlist = (ArrayList<Wish>) wishDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		Product product = productDAO.getProduct(prodId);	
		for(Wish wish: wishlist){
			if(wish.getPid()==prodId)
			{
				wishDAO.add(wish);
				return "redirect:/cart/viewwish";
			}
		}
		
		Wish wish =new Wish();
		wish.setPid(prodId);
		wish.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
		wish.setPname(product.getProdName());
		wish.setPprice(product.getProductPrice());
		
		wishDAO.add(wish);
		return "redirect:/cart/viewwish";


	}

	
	@RequestMapping(value = "cart/viewwish")
	public String viewcart(Model model, HttpSession session) 
	{
		ArrayList<Wish> wishlist = (ArrayList<Wish>) wishDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		model.addAttribute("title", "Wishlist");
		model.addAttribute("wishlist", wishlist);
		model.addAttribute("userClickWish", true);
		model.addAttribute("msg", false);
		return "page";

	}


	@RequestMapping(value = "deletewish/{prodId}")
	public String delcart(@PathVariable int prodId, Model model) {
			wishDAO.delete(prodId);
			return "redirect:/cart/viewwish";
	}

}
