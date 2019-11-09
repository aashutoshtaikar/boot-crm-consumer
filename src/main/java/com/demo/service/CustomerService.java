package com.demo.service;

import java.util.List;

import com.demo.model.Customer;

public interface CustomerService {
	Customer getCustomer(int id);
	List<Customer> getCustomers();
	void addCustomer(Customer customer);
	void deleteCustomer(int id);
	List<Customer> searchCustomers(String name);
	Customer searchId(int id);
}
