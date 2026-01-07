package com.billing.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.billing.entity.Admin;
import com.billing.repository.AdminRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(AdminRepository adminRepository) {
        return args -> {
            if (adminRepository.count() == 0) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword("admin123");
                adminRepository.save(admin);
            }
        };
    }
}
