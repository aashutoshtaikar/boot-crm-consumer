package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import com.demo.model.Customer;
import com.demo.service.CustomerServiceNew;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerServiceNew customerService;
	
	@GetMapping("/list")
	public String getHomepage(final Model model) {
		Flux<Customer> customers = customerService.getCustomers();
//        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
//                new ReactiveDataDriverContextVariable(customers, 1);

		model.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showAddForm")
	public String showAddForm(final Model model) {
		
		model.addAttribute("customer", new Customer());
		
		return "form-customer";
	}
	
	@GetMapping("/showUpdateForm")
	public String showUpdateForm(final Model model, @RequestParam("id") Integer id) {
		Mono<Customer> customer = customerService.getCustomer(id);
		
		model.addAttribute("customer", customer);
		
		return "form-customer";
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		customerService.addCustomer(theCustomer);
		return "redirect:/customers/list";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("id") Integer id) {
		customerService.deleteCustomer(id);
		return "redirect:/customers/list";	
	}
	
	@GetMapping("/searchCustomers")
	public String searchCustomers(final Model model, @RequestParam("theSearchName") String theSearchName) {
		
		Mono<List<Customer>> customers = customerService.searchCustomers(theSearchName);
		
		if (customers!=null) {
			model.addAttribute("customers", customers);	
			model.addAttribute("customersFound", true);
		}
		else {
			model.addAttribute("customers", customerService.getCustomers());
			model.addAttribute("customersFound", false);
		}
		
		
		return "list-customers";
	}
}
