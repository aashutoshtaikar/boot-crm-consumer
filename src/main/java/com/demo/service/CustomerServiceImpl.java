package com.demo.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private WebClient webClient = WebClient.create();
	
	private String restUrl = "http://localhost:8080/customers";
	
	@Override
	public Customer getCustomer(int id) {
		Customer customer = webClient.get()
				.uri(restUrl + "/" + id)
				.retrieve()
				.bodyToMono(Customer.class)
				.block();
		
		return customer;
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = webClient.get()
				.uri(restUrl)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Customer>>(){})
				.block();
		
		return customers;
	}

	@Override
	public void addCustomer(Customer customer) {
		String addedCustomer = webClient.post()
			.uri(restUrl)
			.bodyValue(customer)
			.retrieve()
			.bodyToMono(String.class)
			.block();
	}

	@Override
	public void deleteCustomer(int id) {
		String deleted = webClient.delete()
			.uri(restUrl + "/" + id)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
	}

	@Override
	public List<Customer> searchCustomers(String name) {
		List<Customer> customers = webClient.get()
				.uri(restUrl + "/searchName/" + name)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Customer>>(){})
				.block();

		return customers;
	}

	@Override
	public Customer searchId(int id) {
		Customer customer = webClient.get()
				.uri(restUrl + "/" + id)
				.retrieve()
				.bodyToMono(Customer.class)
				.block();
		
		return customer;
	}

}
