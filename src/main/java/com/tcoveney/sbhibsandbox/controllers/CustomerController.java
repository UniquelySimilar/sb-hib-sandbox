package com.tcoveney.sbhibsandbox.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcoveney.sbhibsandbox.models.Customer;
import com.tcoveney.sbhibsandbox.repositories.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	private CustomerRepository customerRepository;
	
	CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@GetMapping("/")
	List<Customer> findAll() {
		List<Customer> customers = new ArrayList<Customer>();
		for (Customer customer : customerRepository.findAll()) {
			customers.add(customer);
		}
		return customers;
	}
	
	@GetMapping("/{id}")
	Customer findById(@PathVariable Long id, HttpServletResponse response) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		if (customerOptional.isPresent()) {
			return customerOptional.get();
		}
		else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	@PostMapping("/")
	void create(@RequestBody Customer customer, HttpServletResponse response) {
		customerRepository.save(customer);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}
	
	@PutMapping("/")
	void update(@RequestBody Customer customer, HttpServletResponse response) {
		customerRepository.save(customer);
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@DeleteMapping("/{id}")
	void delete(@PathVariable Long id, HttpServletResponse response) {
		Optional<Customer> customerOptional = customerRepository.findById(id);
		Customer customer = customerOptional.get();
		customerRepository.delete(customer);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}
