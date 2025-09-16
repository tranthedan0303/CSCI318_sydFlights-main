package com.example;

import com.example.infrastructure.repository.FlightRepository;
import com.example.model.Flight;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class SydFlightsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SydFlightsApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadDatabase(FlightRepository flightRep) throws Exception {
        return args -> {
            Flight flight1 = new Flight(1L, "MEL", new BigDecimal("185.50"), "Qantas");
            Flight flight2 = new Flight(2L, "BNE", new BigDecimal("240.00"), "Jetstar");
            Flight flight3 = new Flight(3L, "ADL", new BigDecimal("155.00"), "Virgin Australia");

            flightRep.save(flight1);
            flightRep.save(flight2);
            flightRep.save(flight3);
        };
    }
}
