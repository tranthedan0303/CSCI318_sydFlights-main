package com.example;

import com.example.infrastructure.repository.UserRepository;
import com.example.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceDatabaseTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSampleUsersLoaded() {
        List<User> users = userRepository.findAll();

        // Expect 3 users from the CommandLineRunner
        assertThat(users).hasSize(3);

        // Check for specific seeded users
        assertThat(users)
                .extracting(User::getEmail)
                .contains("john.doe@example.com", "jane.smith@example.com", "bob.wilson@example.com");
    }
}
