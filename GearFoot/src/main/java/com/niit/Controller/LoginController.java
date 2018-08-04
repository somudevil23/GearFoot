package com.niit.Controller;

import java.util.Collection;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.pistona.DAO.CategoryDAO;
import com.niit.pistona.DAO.CustomerDAO;
import com.niit.pistona.DAO.ProductDAO;
import com.niit.pistona.Model.Customer;
import com.niit.pistona.Model.UserCredentials;

@Controller
public class LoginController {

	@Autowired
	CategoryDAO categorydao;
	@Autowired
	ProductDAO productdao;
	@Autowired
	CustomerDAO customerdao;
	@RequestMapping(value = "login")
	public String login(Model model) {

		model.addAttribute("title", "Sign In");
		model.addAttribute("regsuccess", false);
		model.addAttribute("userClickLogin", true);
		model.addAttribute("loginerror", false);
		return "page";

	}

	@RequestMapping("/loginerror")
	public String loginFailure(Model model) {
		model.addAttribute("title", "Sign In");
		model.addAttribute("loginerror", true);
		model.addAttribute("userClickLogin", true);
		
		return "page";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("msg", "Loggedout successfully..");
		model.addAttribute("title", "Sign In");
		model.addAttribute("userClickLogin", true);
		model.addAttribute("loginerror", false);

		return "page";
	}

	@RequestMapping("/loginsuccess")
	public String loginsuccess(HttpSession session, Model model) 
	{
		if (session.getAttribute("pid") == null) 
		{
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			@SuppressWarnings("unchecked")
			Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities();
			for (GrantedAuthority authority : authorities) 
			{
				if (authority.getAuthority().equals("ROLE_USER")) 
				{
					Customer customer = customerdao.showCustomer(email);
					session.setAttribute("useremail", customer.getName());
					session.setAttribute("usercartid", customer.getCartId());
					session.setAttribute("userlogin", true);
					//session.setAttribute("cartsize", cartDAO.show(customer.getCartId()).size());
					return "redirect:/productview";
				} 
				else 
				{
					session.setAttribute("useremail", "Admin");
					session.setAttribute("userlogin", false);
					return "redirect:/";
				}
			}
		} 
		else 
		{
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Customer customer = customerdao.showCustomer(email);
			session.setAttribute("useremail", customer.getName());
			session.setAttribute("usercartid", customer.getCartId());
			session.setAttribute("userlogin", true);
			//session.setAttribute("cartsize", cartDAO.show(customer.getCartId()).size());
			int proid = Integer.parseInt(session.getAttribute("pid").toString());
			int qnty = Integer.parseInt(session.getAttribute("qid").toString());
			return "redirect:addprod/" + proid + "?qnty=" + qnty;
		}
		return "redirect:/";
	}

	@RequestMapping(value = "registration")
	public String registration(Model model) {

		model.addAttribute("title", "Sign Up");
		model.addAttribute("userClickRegistration", true);
		model.addAttribute("customer", new Customer());
		return "page";

	}

	@RequestMapping(value = "addcustomer")
	public String setcustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Register");
			model.addAttribute("userClickRegistration", true);
			model.addAttribute("customer", customer);
			model.addAttribute("status", true);
			model.addAttribute("edit", false);

			return "page";
		}

		try {
			customerdao.addCustomer(customer);
			model.addAttribute("title", "Sign In");
			model.addAttribute("regsuccess", true);
			model.addAttribute("userClickLogin", true);
			return "page";
		} catch (Exception e) {
			model.addAttribute("title", "Register");
			model.addAttribute("userClickRegistration", true);
			model.addAttribute("customer", new Customer());
			model.addAttribute("status", true);
			return "page";
		}

	}

	/*@RequestMapping(value = "cart/changepassword")
	public String changepassword(Model model) {

		model.addAttribute("title", "Change Password");

		model.addAttribute("categorylist", categoryDAO.list());
		model.addAttribute("userClickChangePassword", true);
		model.addAttribute("usercred", new UserCredentials());
		model.addAttribute("msg", false);
		return "page";

	}

	@RequestMapping(value = "cart/updatepassword")
	public String updatepassword(@ModelAttribute("usercred") UserCredentials uc, Model model,
			HttpServletRequest request) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		UserCredentials olduc = customerDAO.showcred(email);

		String oldpassword = request.getParameter("oldpass");

		if (oldpassword.equals(olduc.getPassword())) {
			olduc.setPassword(uc.getPassword());
			customerDAO.saveorupdatepassword(olduc);
			model.addAttribute("title", "Change Password");
			model.addAttribute("categorylist", categoryDAO.list());
			model.addAttribute("userClickChangePassword", true);
			model.addAttribute("usercred", new UserCredentials());
			model.addAttribute("msg", true);

		} else {
			model.addAttribute("title", "Change Password");
			model.addAttribute("categorylist", categoryDAO.list());
			model.addAttribute("userClickChangePassword", true);
			model.addAttribute("usercred", new UserCredentials());
			model.addAttribute("msg1", true);
		}

		return "page";

	}

	@RequestMapping(value = "/resetpassword")
	public String resetpassword(Model model, HttpServletRequest request) {
		String email = request.getParameter("j_username");

		UserCredentials olduc = customerDAO.showcred(email);
		if (olduc != null) {
			String s = UUID.randomUUID().toString().substring(0, 8);
			System.out.println(s);
			String finalmessage = "Hi,\n\n your new password is \n\n " + s + " \n\n regards\n\n Admin";
			SimpleMailMessage pemail = new SimpleMailMessage();
			pemail.setTo(email);
			pemail.setSubject("Your new password for computer mart");
			pemail.setText(finalmessage);
			mailSender.send(pemail);
			olduc.setPassword(s);
			customerDAO.saveorupdatepassword(olduc);
			model.addAttribute("title", "Sign In");
			model.addAttribute("categorylist", categoryDAO.list());
			model.addAttribute("userClickLogin", true);
			model.addAttribute("loginerror", false);

		} else {
			model.addAttribute("title", "Sign In");
			model.addAttribute("categorylist", categoryDAO.list());
			model.addAttribute("userClickLogin", true);
			model.addAttribute("loginerror", true);

		}

		return "page";

	}*/
}
