package com.niit.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.pistona.DAO.CartDAO;
import com.niit.pistona.DAO.CategoryDAO;
import com.niit.pistona.DAO.ProductDAO;
import com.niit.pistona.Model.Cart;
import com.niit.pistona.Model.Product;

@Controller
public class CartController {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private CartDAO cartDAO;

	@RequestMapping(value = "addprod/{id}")
	public String addcart(@PathVariable int id, @RequestParam(value = "qnty") int qnty, Model model,
			HttpSession session) {
		if (session.getAttribute("usercartid") != null) {
			ArrayList<Cart> cartlist = (ArrayList<Cart>) cartDAO
					.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
			Product product = productDAO.getProduct(id);

			if (product.getProductQuantity() >= qnty) {

				for (Cart cartItem : cartlist) {
					if (cartItem.getPid() == id) {

						cartItem.setQty(qnty);
						cartItem.setTotal((qnty) * (product.getProductPrice()));
						cartDAO.add(cartItem);
						return "redirect:/cart/viewcart";
					}
				}

				Cart cart = new Cart();
				cart.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
				cart.setPid(product.getProdId());
				cart.setPname(product.getProdName());
				cart.setQty(qnty);
				cart.setPprice(product.getProductPrice());
				cart.setTotal((qnty) * (product.getProductPrice()));
				
				cartDAO.add(cart);
				return "redirect:/cart/viewcart";
			} else {
				model.addAttribute("msg", true);
				model.addAttribute("productlist", productDAO.showallProduct());
				model.addAttribute("product", productDAO.getProduct(id));
				model.addAttribute("title", "Product Info");
				model.addAttribute("userClickProductInfo", true);
				//model.addAttribute("categorylist", categoryDAO.list());
				return "page";
			}
		}
		else
		{
			session.setAttribute("pid",id);
			session.setAttribute("qid",qnty);
			return "redirect:/login";
		}

	}

	@RequestMapping(value = "cart/viewcart")
	public String viewcart(Model model, HttpSession session) {
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		model.addAttribute("title", "Cart");
		model.addAttribute("cartlist", cartlist);
	//	model.addAttribute("categorylist", categoryDAO.list());
		model.addAttribute("userClickCart", true);
		model.addAttribute("msg", false);
		session.setAttribute("cartsize", cartlist.size());
		return "page";

	}

	@RequestMapping(value = "deletecart/{id}")
	public String delcart(@PathVariable int id, Model model) {
		cartDAO.delete(id);
		return "redirect:/cart/viewcart";
	}
}
