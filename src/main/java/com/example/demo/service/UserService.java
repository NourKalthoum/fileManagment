package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    

    @Autowired
	RoleRepo roleRepository;
    @Autowired
    UserRepository userRepository;
    
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public User getUser(long id) {
		
		return userRepository.findById(id);
	}
}
