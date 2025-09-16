package com.example.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class  FlightDTO {

    private Long id;
    private String flightId;
    private String origin; // fixed to "SYD"
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String duration;
    private BigDecimal price;
    private String currency;
    private String airline;

    public FlightDTO() {}
}
