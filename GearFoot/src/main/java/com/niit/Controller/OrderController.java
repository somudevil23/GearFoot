package com.niit.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.pistona.DAO.CartDAO;
import com.niit.pistona.DAO.CategoryDAO;
import com.niit.pistona.DAO.CustomerDAO;
import com.niit.pistona.DAO.OrderDAO;
import com.niit.pistona.DAO.ProductDAO;
import com.niit.pistona.DAO.ShipAddressDAO;
import com.niit.pistona.Model.Cart;
import com.niit.pistona.Model.Customer;
import com.niit.pistona.Model.CustomerOrder;
import com.niit.pistona.Model.Product;
@Controller
public class OrderController {
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private ShipAddressDAO addressDAO;
	
	@RequestMapping(value = "invoice/{aid}")
	public String invoice(@PathVariable int aid,Model model,HttpSession session) {
		ArrayList<Cart> cartlist = (ArrayList<Cart>) cartDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString()));
		Long uuid=UUID.randomUUID().getMostSignificantBits();
		String id="OD"+uuid.toString().replace('-', '2');
		Iterator<Cart> cartiterator=cartlist.listIterator();
		while (cartiterator.hasNext())
		{
			Cart cart=cartiterator.next();
			Product product=productDAO.getProduct(cart.getPid());
			product.setProductQuantity(product.getProductQuantity()-cart.getQty());
			productDAO.saveOrUpdateProduct(product);
			
			CustomerOrder c=new CustomerOrder();
			c.setCartId(cart.getCartId());
			c.setOrderId(id);
			c.setAddid(aid);
			c.setDate(new Date());
			c.setPid(cart.getPid());
			c.setPname(cart.getPname());
			c.setQty(cart.getQty());
			c.setSubtotal(cart.getTotal());
			orderDAO.insert(c);
			cartDAO.delete(cart.getId());
			
		}
		
		return "redirect:/viewbill/"+id+"/"+aid;
	}
	
	@RequestMapping(value = "viewbill/{oid}/{aid}")
	public String viewbill(Model model, HttpSession session,@PathVariable String oid,@PathVariable int aid) 
	{
		List<CustomerOrder> custorder=orderDAO.viewreceipt(oid);
		model.addAttribute("title", "Order");
		model.addAttribute("userClickInvoice", true);
		model.addAttribute("baddress",addressDAO.show(aid));
		model.addAttribute("orderid",oid);
		model.addAttribute("orderdetail",custorder);
		session.setAttribute("cartsize", cartDAO
				.show(Integer.parseInt(session.getAttribute("usercartid").toString())).size());
		return "page";

	}
	
	@RequestMapping(value="viewallorders")
	public String viewallorders(Model model, HttpSession session) {
		
		int cartid = Integer.parseInt(session.getAttribute("usercartid").toString());
		model.addAttribute("title", "My Orders");
		model.addAttribute("orderlist",orderDAO.viewAllOrder(cartid));
		model.addAttribute("userClickAllOrder", true);
		return "page";
	}
}
