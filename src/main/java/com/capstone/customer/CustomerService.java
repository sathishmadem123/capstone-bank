package com.capstone.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	public boolean existByPan(String Pan) {
		return customerRepo.existsByPanNumberIgnoreCase(Pan);
	}
	
	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	public void deleteCustomer(String pan) {
		customerRepo.deleteByPanNumber(pan);
	}
	
	public Customer findCustomerByPan(String pan) {
		return customerRepo.findByPanNumber(pan);
	}
}
