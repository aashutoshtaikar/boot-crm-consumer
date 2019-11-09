package com.demo.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	private RestTemplate restTemplate;
	
	public CustomerServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	private String restUrl = "http://localhost:8080/customers";
	
	@Override
	public Customer getCustomer(int id) {
		ResponseEntity<Customer> response = restTemplate.getForEntity(restUrl + "/" + id, Customer.class);
		return response.getBody();
	}

	@Override
	public List<Customer> getCustomers() {
		ResponseEntity<List<Customer>> response = restTemplate.exchange(restUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>(){});
		return response.getBody();
	}

	@Override
	public void addCustomer(Customer customer) {
		restTemplate.postForEntity(restUrl, customer, String.class);
	}

	@Override
	public void deleteCustomer(int id) {
		restTemplate.delete(restUrl + "/" + id);
		
	}

	@Override
	public List<Customer> searchCustomers(String name) {
		ResponseEntity<List<Customer>> response = restTemplate.exchange(restUrl + "/searchName/" + name, HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>(){});
		return response.getBody();
	}

	@Override
	public Customer searchId(int id) {
		ResponseEntity<Customer> response = restTemplate.getForEntity(restUrl + "/" + id, Customer.class);
		return response.getBody();
	}

}
