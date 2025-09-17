package com.example.infrastructure.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find active users
    List<User> findByActive(boolean active);

    // Find users by preferred airline
    List<User> findByPreferredAirline(String airline);

    // Find users who have email notifications enabled
    List<User> findByEmailNotificationsTrue();

    // Find users who have SMS notifications enabled
    List<User> findBySmsNotificationsTrue();

    // Custom query to find users by frequent destination
    @Query("SELECT u FROM User u JOIN u.frequentDestinations d WHERE d = :destination")
    List<User> findByFrequentDestination(@Param("destination") String destination);

    // Check if email exists
    boolean existsByEmail(String email);
}