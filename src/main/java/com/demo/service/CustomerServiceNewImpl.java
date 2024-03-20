package com.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Primary
public class CustomerServiceNewImpl implements CustomerServiceNew {

	private WebClient webClient;
	
	public CustomerServiceNewImpl() {
		this.webClient = WebClient.create();
	}
	
	private String restUrl = "http://localhost:8080/customers";
	
	@Override
	public Mono<Customer> getCustomer(int id) {
		Mono<Customer> response = webClient
				.get()
				.uri(builder -> 
					builder.path(restUrl + "/" + id)
						.build())
				.retrieve()
				.bodyToMono(Customer.class);

		return response;
	}

	@Override
	public Flux<Customer> getCustomers() {
		Flux<Customer> response = webClient
				.get()
				.uri(builder -> 
					builder.path(restUrl)
						.build())
				.retrieve()
				.bodyToFlux(Customer.class);
		
		return response;
	}

	@Override
	public void addCustomer(Customer customer) {
		webClient.post()
			.uri(builder -> builder
					.path(restUrl)
					.build())
			.retrieve()
			.bodyToMono(String.class);
	}

	@Override
	public void deleteCustomer(int id) {
		webClient
			.delete()
			.uri(builder -> builder
					.path(restUrl + "/" + id)
					.build());
		
		
	}

	@Override
	public Mono<List<Customer>> searchCustomers(String name) {
		Mono<List<Customer>> response = webClient
				.get()
				.uri(builder -> builder
						.path(restUrl + "/searchName/" + name)
						.build())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Customer>>(){});
		

		return response;
	}

	@Override
	public Mono<Customer> searchId(int id) {
		Mono<Customer> response = webClient
				.get()
				.uri(builder -> builder
						.path(restUrl + "/" + id)
						.build())
				.retrieve()
				.bodyToMono(Customer.class);
		
		return response;
	}

}
