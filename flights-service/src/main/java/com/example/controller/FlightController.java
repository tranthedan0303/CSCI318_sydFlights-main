package com.example.controller;

import com.example.infrastructure.repository.FlightRepository;
import com.example.model.Flight;
import com.example.service.FlightAgent;
import com.example.service.FlightService;
import com.example.service.dto.FlightDTO;
import dev.langchain4j.service.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController {
    private final FlightService flightService;
    private final FlightAgent flightAgent;

    FlightController(FlightService flightService, FlightAgent flightAgent) {
        this.flightService = flightService;
        this.flightAgent = flightAgent;
    }

    @GetMapping("/flights")
    List<FlightDTO> allFlights() { return flightService.findAll(); }

    @GetMapping("/FlightAgent")
    public String askAgent(@RequestParam String sessionId, @RequestParam String userMessage) {
        Result<String> result = flightAgent.answer(sessionId, userMessage);
        return result.content();
    }
}
