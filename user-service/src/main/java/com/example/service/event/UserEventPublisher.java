package com.example.service.event;

import com.example.model.User;
import com.example.service.dto.UserEventDTO;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    private final StreamBridge streamBridge;

    public UserEventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    // Publish user created event
    public void publishUserCreatedEvent(User user) {
        UserEventDTO event = new UserEventDTO();
        event.setEventType("USER_CREATED");
        event.setUserId(user.getId());
        event.setEmail(user.getEmail());
        event.setFirstName(user.getFirstName());
        event.setLastName(user.getLastName());
        event.setPreferredAirline(user.getPreferredAirline());

        streamBridge.send("user-events-out-0", event);
        System.out.println("Published USER_CREATED event for user: " + user.getEmail());
    }

    // Publish user updated event
    public void publishUserUpdatedEvent(User user) {
        UserEventDTO event = new UserEventDTO();
        event.setEventType("USER_UPDATED");
        event.setUserId(user.getId());
        event.setEmail(user.getEmail());
        event.setFirstName(user.getFirstName());
        event.setLastName(user.getLastName());
        event.setPreferredAirline(user.getPreferredAirline());

        streamBridge.send("user-events-out-0", event);
        System.out.println("Published USER_UPDATED event for user: " + user.getEmail());
    }

    // Publish user deleted event
    public void publishUserDeletedEvent(User user) {
        UserEventDTO event = new UserEventDTO();
        event.setEventType("USER_DELETED");
        event.setUserId(user.getId());
        event.setEmail(user.getEmail());

        streamBridge.send("user-events-out-0", event);
        System.out.println("Published USER_DELETED event for user: " + user.getEmail());
    }
}