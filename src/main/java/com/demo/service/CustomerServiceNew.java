package com.demo.service;

import java.util.List;

import com.demo.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerServiceNew {
	Mono<Customer> getCustomer(int id);
	Flux<Customer> getCustomers();
	void addCustomer(Customer customer);
	void deleteCustomer(int id);
	Mono<List<Customer>> searchCustomers(String name);
	Mono<Customer> searchId(int id);
}
