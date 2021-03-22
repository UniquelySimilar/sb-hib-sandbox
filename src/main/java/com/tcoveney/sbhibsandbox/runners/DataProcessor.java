package com.tcoveney.sbhibsandbox.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tcoveney.sbhibsandbox.models.Customer;
import com.tcoveney.sbhibsandbox.repositories.CustomerRepository;

//@Component
public class DataProcessor implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(DataProcessor.class);
	
	@Autowired
	CustomerRepository repository;

	@Override
	public void run(String... args) throws Exception {
		log.info("DataProcessor.run() starting");
		
		List<Customer> customers = new ArrayList<Customer>();

		// save a few customers
		customers.add(repository.save(new Customer("Jack", "Bauer")));
		customers.add(repository.save(new Customer("Chloe", "O'Brian")));
		customers.add(repository.save(new Customer("Kim", "Bauer")));
		customers.add(repository.save(new Customer("David", "Palmer")));
		customers.add(repository.save(new Customer("Michelle", "Dessler")));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer customer : repository.findAll()) {
			log.info(customer.toString());
		}
		log.info("");

		// fetch an individual customer by ID
		Long firstCustomerId = customers.get(0).getId();
		Optional<Customer> customerOptional = repository.findById(firstCustomerId);
		if (!customerOptional.isPresent()) {
			log.warn(String.format("Customer with ID %d not found", firstCustomerId));
			log.info("Exiting DataProcessor.run()");
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
		repository.findByLastName("Bauer").forEach(bauer -> {
			log.info(bauer.toString());
		});
		log.info("");

		// delete customers
		customers.forEach(customerObj -> {
			repository.delete(customerObj);
		});

		log.info("DataProcessor.run() finished");
	}

}
