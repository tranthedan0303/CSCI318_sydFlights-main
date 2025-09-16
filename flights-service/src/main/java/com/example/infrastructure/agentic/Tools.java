package com.example.infrastructure.agentic;

import com.example.service.FlightService;
import com.example.service.dto.FlightDTO;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Tools {
    private static final Logger log = LoggerFactory.getLogger(Tools.class);
    private final FlightService flightService;

    public Tools(FlightService flightService) {
        this.flightService = flightService;
    }

    @Tool
    public List<FlightDTO> getFlightToDestination(String dest) {
        log.info("Tool:getFlightToDestination called with dest='{}'", dest);
        List<FlightDTO> flights = flightService.findByDestination(dest);
        log.info("Tool:getFlightToDestination returning {} flights", flights.size());
        return flights;
    }

//    @Tool
//    public void saveTicket(String bookingNumber, String customerName, String customerSurname) {
//        bookingService.cancelBooking(bookingNumber, customerName, customerSurname);
//    }
}