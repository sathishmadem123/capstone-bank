package com.capstone.customer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	public boolean existsByPanNumberIgnoreCase(String panNumber);
	
	@Modifying
	@Transactional
	public void deleteByPanNumber(String panNumber);
	
	public Customer findByPanNumber(String panNumber);
}