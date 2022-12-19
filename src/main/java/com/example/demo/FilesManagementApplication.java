package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.demo.entity.ERole;
import com.example.demo.entity.Role;
import com.example.demo.service.StorageService;
import com.example.demo.service.UserService;

@Configuration
@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass=true)
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

	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("file");
    }	
	
}
