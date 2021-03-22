package com.tcoveney.sbhibsandbox.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/testrepo")
	void testRepo() {
		// save a few customers
		customerRepository.save(new Customer("Jack", "Bauer"));
		customerRepository.save(new Customer("Chloe", "O'Brian"));
		customerRepository.save(new Customer("Kim", "Bauer"));
		customerRepository.save(new Customer("David", "Palmer"));
		customerRepository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		List<Customer> customers = new ArrayList<Customer>();
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			log.info(customer.toString());
			customers.add(customer);
		}
		log.info("");

		// fetch an individual customer by ID
		Long firstCustomerId = customers.get(0).getId();
		Optional<Customer> customerOptional = customerRepository.findById(firstCustomerId);
		if (!customerOptional.isPresent()) {
			log.warn(String.format("Customer with ID %d not found", firstCustomerId));
			log.info("Exiting testRepo()");
			return;
		}
		Customer customer = customerOptional.get();
		log.info("Customer found with findById():");
		log.info("--------------------------------");
		log.info(customer.toString());
		log.info("");

		// fetch customers by last name
		log.info("Customer found with findByLastName('Bauer'):");
		log.info("--------------------------------------------");
		customerRepository.findByLastName("Bauer").forEach(bauer -> {
			log.info(bauer.toString());
		});
		log.info("");
	}
	
	@DeleteMapping("/deleteall")
	void deleteAll() {
		for (Customer customer : customerRepository.findAll()) {
			customerRepository.delete(customer);
		}
	}

}
