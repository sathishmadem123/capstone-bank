package com.capstone.admin;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long>{
	
	public boolean existsByEmail(String email);
	
	public Admin findByEmail(String email);
}
