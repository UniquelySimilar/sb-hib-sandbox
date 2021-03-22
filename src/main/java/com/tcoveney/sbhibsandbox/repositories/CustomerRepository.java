package com.tcoveney.sbhibsandbox.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tcoveney.sbhibsandbox.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);

	Customer findById(long id);
}
