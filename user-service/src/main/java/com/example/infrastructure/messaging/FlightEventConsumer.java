package com.example.infrastructure.messaging;

import com.example.service.dto.FlightPriceChangedDTO;
import com.example.service.UserService;
import com.example.service.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Consumer;

@Configuration
public class FlightEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(FlightEventConsumer.class);
    private final UserService userService;

    public FlightEventConsumer(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public Consumer<FlightPriceChangedDTO> flightEventsIn() {
        return event -> {
            log.info("Received flight price change event for flight: {}", event.getFlightId());

            // Here you could implement logic to notify users about price changes
            // For example, find users interested in this flight's destination
            // and send them notifications based on their preferences

            // Example: Get all users with email notifications enabled
            List<UserDTO> usersToNotify = userService.getUsersForEmailNotifications();

            for (UserDTO user : usersToNotify) {
                log.info("Would notify user {} about flight {} price change to {}",
                        user.getEmail(), event.getFlightId(), event.getNewPrice());
                // In a real implementation, you would send this to a notification service
            }
        };
    }
}