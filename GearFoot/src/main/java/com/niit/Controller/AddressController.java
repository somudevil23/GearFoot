package com.niit.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.pistona.DAO.ShipAddressDAO;
import com.niit.pistona.Model.Product;
import com.niit.pistona.Model.ShipAddress;

@Controller
public class AddressController {
	
	@Autowired
	private ShipAddressDAO addressDAO;
	
	@RequestMapping(value = "address")
	public String address(Model model,HttpSession session) {
		
		model.addAttribute("title", "Address");
		model.addAttribute("userClickCheckOut", true);
		model.addAttribute("address", new ShipAddress());
		model.addAttribute("addresslist",addressDAO.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
		return "page";
	}
	
	@RequestMapping(value = "setaddress")
	public String setCategory(@Valid @ModelAttribute("address") ShipAddress address,BindingResult result, Model model,HttpSession session) {
		
		if(result.hasErrors()) {
			model.addAttribute("title", "Address");
			model.addAttribute("userClickCheckOut", true);
			model.addAttribute("address", address);
			model.addAttribute("addresslist",addressDAO.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
			
			return "page";
		}

		try {
			
			address.setCartId(Integer.parseInt(session.getAttribute("usercartid").toString()));
			addressDAO.add(address);
			return "redirect:/address";
		} catch (Exception e) {
			model.addAttribute("title", "Address");
			model.addAttribute("userClickCheckOut", true);
			model.addAttribute("address", address);
			model.addAttribute("addresslist",addressDAO.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
			return "page";
		}

	}

	@RequestMapping(value = "editadd")
	public String editAddress(@RequestParam int id, Product product, Model model,HttpSession session ) {
		model.addAttribute("title", "Address");
		model.addAttribute("userClickCheckOut", true);
		model.addAttribute("address", addressDAO.show(id));
		model.addAttribute("addresslist",addressDAO.list(Integer.parseInt(session.getAttribute("usercartid").toString())));
		return "page";
	}
	
	@RequestMapping(value = "deladd/{id}")
	public String deladd(@PathVariable int id, Model model) {
		addressDAO.delete(id);
		return "redirect:/address";
	}


}
