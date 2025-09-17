package com.example.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEventDTO {
    private String eventType; // USER_CREATED, USER_UPDATED, USER_DELETED
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String preferredAirline;
    private LocalDateTime timestamp = LocalDateTime.now();

    public UserEventDTO() {}
}