package com.example.service;

import com.example.infrastructure.repository.FlightRepository;
import com.example.model.Flight;
import com.example.service.dto.FlightDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
    // convert entity to DTO
//    private FlightDTO convertToDTO(Flight flight) {
//        FlightDTO dto = new FlightDTO();
//        dto.setId(flight.getId());
//        return dto;
//    }
    private FlightDTO convertToDTO(Flight flight) {
        FlightDTO dto = new FlightDTO();
        dto.setId(flight.getId());
        dto.setFlightId(flight.getFlightId());
        dto.setDestination(flight.getDestination());
        dto.setDepartureDate(flight.getDepartureDate());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setDuration(flight.getDuration());
        dto.setPrice(flight.getPrice());
        dto.setCurrency(flight.getCurrency());
        dto.setAirline(flight.getAirline());
        return dto;
    }

    public List<FlightDTO> findAll() {
        return flightRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> findByDestination(String dest) {
        return flightRepository.findByDestination(dest).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    //When User?
//    public Flight save(Flight flight) {
//        return flightRepository.save(flight);
//    }
}
