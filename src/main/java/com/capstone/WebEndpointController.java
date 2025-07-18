package com.capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.admin.Admin;
import com.capstone.admin.AdminService;
import com.capstone.customer.Customer;
import com.capstone.customer.CustomerService;

@Controller
public class WebEndpointController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/admin-login")
	public String showAdminLoginPage() {
		return "admin-login";
	}

	@RequestMapping(value = "/admin-authentication", method = RequestMethod.POST)
	public String authenticateAdmin(@ModelAttribute Admin adminCredentials, Model model) {

		if (adminService.existByEmail(adminCredentials.getEmail())) {
			Admin adminCredentialsInDb = adminService.findByEmail(adminCredentials.getEmail());
			if (adminCredentials.getPassword().equals(adminCredentialsInDb.getPassword()))
				return "/admin-login-successful";
			else {
				model.addAttribute("pageTitle", "Login failed");
				model.addAttribute("heading", "Login falied");
				model.addAttribute("message", "please check your credentials");
				model.addAttribute("hLink", "/admin-login");
				model.addAttribute("linkText", "back");
				return "display-message";
			}
		} else {
			model.addAttribute("pageTitle", "Login failed");
			model.addAttribute("heading", "Login falied");
			model.addAttribute("message", "please check your credentials");
			model.addAttribute("hLink", "/admin-login");
			model.addAttribute("linkText", "back");
			return "display-message";
		}
	}

	@RequestMapping(value = "/CustomerEntry", method = RequestMethod.GET)
	public String customerEntryForm() {
		return "customer-entry";
	}

	@GetMapping("/CustomerDelete")
	public String CustomerdeletionForm() {
		return "customer-delete";
	}

	@PostMapping("/addCustomer")
	public String addCustomer(@ModelAttribute Customer customer, Model model) {
		model.addAttribute("linkText", "back");
		if (customerService.existByPan(customer.getPanNumber())) {
			model.addAttribute("pageTitle", "Customer exists");
			model.addAttribute("heading", "Unable to add Customer");
			model.addAttribute("message", "Customer already exists in database");
			model.addAttribute("hLink", "/CustomerEntry");
		} else {
			customerService.saveCustomer(customer);
			model.addAttribute("pageTitle", "Customer saved");
			model.addAttribute("heading", "Customer Added");
			model.addAttribute("message", "Customer details saved into database successfully");
			model.addAttribute("hLink", "/admin-login-successful");
		}
		return "display-message";
	}

	@DeleteMapping("/deleteCustomer")
	public ModelAndView deleteCustomer(@RequestParam("panNumber") String pan) {
		ModelAndView modelAndView = new ModelAndView("display-message");
		modelAndView.addObject("linkText", "back");
		if (!customerService.existByPan(pan)) {
			modelAndView.addObject("pageTitle", "Customer not found");
			modelAndView.addObject("heading", "Unable to delete Customer");
			modelAndView.addObject("message", "Customer with pan number " + pan + " is not available");
			modelAndView.addObject("hLink", "/CustomerDelete");
		} else {
			customerService.deleteCustomer(pan);
			modelAndView.addObject("pageTitle", "Customer deleted");
			modelAndView.addObject("heading", "Customer details deleted successfully");
			modelAndView.addObject("message", "Customer with pan number " + pan + " is deleted");
			modelAndView.addObject("hLink", "/admin-login-successful");
		}
		return modelAndView;
	}

	@GetMapping("/admin-login-successful")
	public String returnAdminPage() {
		return "admin-login-successful";
	}

	@GetMapping("/customer-eligibility-check")
	public String showCustmerEntryPage() {
		return "customer-eligibility-check";
	}

	@PostMapping("/customer-eligibility-check")
	public String customerEligibilityCheck(@RequestParam("panNumber") String pan, Model model) {
		if (!customerService.existByPan(pan)) {
			model.addAttribute("pageTitle", "Customer not found");
			model.addAttribute("heading", "Invalid Entry!!");
			model.addAttribute("message", "Pan number " + pan + " is not found in the database");
			model.addAttribute("hLink", "/customer-eligibility-check");
			model.addAttribute("linkText", "Enter Again");
		} else {
			Customer customer = customerService.findCustomerByPan(pan);
			if (customer.getCreditScore() >= 5) {
				model.addAttribute("pageTitle", "Eligible");
				model.addAttribute("heading", "Congratulations!!");
				model.addAttribute("message", "You are eligible for a credit card");
				model.addAttribute("hLink", "/customer-eligibility-check");
				model.addAttribute("linkText", "back");
			} else {
				model.addAttribute("pageTitle", "Not eligible");
				model.addAttribute("heading", "Sorry!!");
				model.addAttribute("message", "You are not eligible for a credit card");
				model.addAttribute("hLink", "/customer-eligibility-check");
				model.addAttribute("linkText", "back");
			}
		}
		return "display-message";
	}
}
