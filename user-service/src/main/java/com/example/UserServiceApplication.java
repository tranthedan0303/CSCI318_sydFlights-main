package com.example;

import com.example.infrastructure.repository.UserRepository;
import com.example.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadDatabase(UserRepository userRepository) throws Exception {
        return args -> {
            // Preload some sample users
            User user1 = new User();
            user1.setEmail("john.doe@example.com");
            user1.setFirstName("John");
            user1.setLastName("Doe");
            user1.setPhoneNumber("+61412345678");
            user1.setPreferredAirline("Qantas");
            user1.setCreatedAt(LocalDateTime.now());
            user1.setActive(true);

            User user2 = new User();
            user2.setEmail("jane.smith@example.com");
            user2.setFirstName("Jane");
            user2.setLastName("Smith");
            user2.setPhoneNumber("+61423456789");
            user2.setPreferredAirline("Virgin Australia");
            user2.setCreatedAt(LocalDateTime.now());
            user2.setActive(true);

            User user3 = new User();
            user3.setEmail("bob.wilson@example.com");
            user3.setFirstName("Bob");
            user3.setLastName("Wilson");
            user3.setPhoneNumber("+61434567890");
            user3.setPreferredAirline("Jetstar");
            user3.setCreatedAt(LocalDateTime.now());
            user3.setActive(true);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            System.out.println("Sample users loaded into database");
        };
    }
}