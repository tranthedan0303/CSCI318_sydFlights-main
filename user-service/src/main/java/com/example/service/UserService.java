package com.example.service;

import com.example.infrastructure.repository.UserRepository;
import com.example.model.User;
import com.example.service.dto.UserDTO;
import com.example.service.event.UserEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserEventPublisher eventPublisher;

    public UserService(UserRepository userRepository, UserEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    // Convert Entity to DTO
    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setPreferredAirline(user.getPreferredAirline());
        dto.setActive(user.isActive());
        dto.setEmailNotifications(user.isEmailNotifications());
        dto.setSmsNotifications(user.isSmsNotifications());
        dto.setFrequentDestinations(user.getFrequentDestinations());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    // Convert DTO to Entity
    private User convertToEntity(UserDTO dto) {
        User user = new User();
        if (dto.getId() != null) {
            user.setId(dto.getId());
        }
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPreferredAirline(dto.getPreferredAirline());
        user.setActive(dto.isActive());
        user.setEmailNotifications(dto.isEmailNotifications());
        user.setSmsNotifications(dto.isSmsNotifications());
        if (dto.getFrequentDestinations() != null) {
            user.setFrequentDestinations(dto.getFrequentDestinations());
        }
        return user;
    }

    // Find all users
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Find user by ID
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Find user by email
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::convertToDTO);
    }

    // Create new user
    public UserDTO createUser(UserDTO userDTO) {
        // Check if email already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("User with email " + userDTO.getEmail() + " already exists");
        }

        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);

        // Publish user created event
        eventPublisher.publishUserCreatedEvent(savedUser);

        return convertToDTO(savedUser);
    }

    // Update existing user
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update fields
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setPreferredAirline(userDTO.getPreferredAirline());
        existingUser.setEmailNotifications(userDTO.isEmailNotifications());
        existingUser.setSmsNotifications(userDTO.isSmsNotifications());

        if (userDTO.getFrequentDestinations() != null) {
            existingUser.setFrequentDestinations(userDTO.getFrequentDestinations());
        }

        User updatedUser = userRepository.save(existingUser);

        // Publish user updated event
        eventPublisher.publishUserUpdatedEvent(updatedUser);

        return convertToDTO(updatedUser);
    }

    // Delete user (soft delete - mark as inactive)
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setActive(false);
        userRepository.save(user);

        // Publish user deleted event
        eventPublisher.publishUserDeletedEvent(user);
    }

    // Find users by preferred airline
    public List<UserDTO> findByPreferredAirline(String airline) {
        return userRepository.findByPreferredAirline(airline).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Find users interested in a destination
    public List<UserDTO> findUsersInterestedInDestination(String destination) {
        return userRepository.findByFrequentDestination(destination).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get users with notification preferences
    public List<UserDTO> getUsersForEmailNotifications() {
        return userRepository.findByEmailNotificationsTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUsersForSmsNotifications() {
        return userRepository.findBySmsNotificationsTrue().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}