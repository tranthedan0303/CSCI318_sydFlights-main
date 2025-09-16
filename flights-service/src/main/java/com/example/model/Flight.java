package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Flight {

    @Id
    private Long id;

    @Column
    private String flightId; // from Amadeus API
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private String duration;
    private BigDecimal price;
    private String currency;
    private String airline;

    public Flight() {}

    public Flight(Long id, String dest, BigDecimal price, String airline) {
        this.id = id;
        this.destination = dest;
        this.price = price;
        this.airline = airline;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", price=" + price + '\'' +
                ", airline=" + airline +
                '}';
    }
}