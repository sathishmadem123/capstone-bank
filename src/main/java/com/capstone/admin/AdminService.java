package com.capstone.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	public boolean existByEmail(String email) {
		return adminRepo.existsByEmail(email);
	}
	
	public Admin findByEmail(String email) {
		return adminRepo.findByEmail(email);
	}

}
