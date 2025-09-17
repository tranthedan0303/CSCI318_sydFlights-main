package com.example.service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String preferredAirline;
    private boolean active;
    private boolean emailNotifications;
    private boolean smsNotifications;
    private List<String> frequentDestinations;
    private LocalDateTime createdAt;

    // Constructor
    public UserDTO() {}
}