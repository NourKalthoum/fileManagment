package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.service.StorageService;
import com.example.demo.service.UserService;

@SpringBootApplication
public class FilesManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesManagementApplication.class, args);
	}

	@Bean
    CommandLineRunner run(UserService userService) {
		// ,StorageService imageStorage
        return args -> {

            userService.saveRole(new Role(1, ERole.ROLE_USER));
            userService.saveRole(new Role(2, ERole.ROLE_ADMIN));
			// imageStorage.init();
		};
	}
		
	
}
