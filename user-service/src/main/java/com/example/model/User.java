package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String preferredAirline;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean active = true;

    // Store user preferences for notifications
    @Column
    private boolean emailNotifications = true;

    @Column
    private boolean smsNotifications = false;

    // Frequent destinations for personalized recommendations
    @ElementCollection
    @CollectionTable(name = "user_frequent_destinations",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "destination")
    private List<String> frequentDestinations = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}