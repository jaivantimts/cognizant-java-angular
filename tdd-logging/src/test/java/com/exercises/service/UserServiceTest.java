package com.exercises.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Mockito Exercises:
 * - Exercise 1: Mocking and Stubbing
 * - Exercise 2: Verifying Interactions
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Mock the dependency

    @InjectMocks
    private UserService userService;        // Inject mock into service

    @Test
    void testFindUserById_UserExists() {
        // Exercise 1: Mocking and Stubbing
        // Arrange
        int userId = 1;
        User mockUser = new User(userId, "John Doe", "john@example.com");
        when(userRepository.findUserById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        User result = userService.findUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());

        // Exercise 2: Verifying Interactions
        verify(userRepository, times(1)).findUserById(userId);
        System.out.println("✅ testFindUserById_UserExists passed");
    }

    @Test
    void testFindUserById_UserNotFound() {
        // Exercise 1: Stubbing a mock to return empty
        // Arrange
        int userId = 999;
        when(userRepository.findUserById(userId)).thenReturn(Optional.empty());

        // Act
        User result = userService.findUser(userId);

        // Assert
        assertNull(result);

        // Exercise 2: Verify interaction happened
        verify(userRepository, times(1)).findUserById(userId);
        System.out.println("✅ testFindUserById_UserNotFound passed");
    }

    @Test
    void testRegisterUser_Success() {
        // Exercise 1: Mocking - no return value (void method)
        // Arrange
        User newUser = new User(2, "Jane Smith", "jane@example.com");

        // Act
        userService.registerUser(newUser);

        // Exercise 2: Verifying that saveUser was called exactly once with the correct user
        verify(userRepository, times(1)).saveUser(newUser);
        System.out.println("✅ testRegisterUser_Success passed");
    }

    @Test
    void testRegisterUser_InvalidUser_ThrowsException() {
        // Arrange
        User invalidUser = new User(3, null, "null@example.com");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(invalidUser);
        });

        // Exercise 2: Verify that saveUser was NEVER called
        verify(userRepository, never()).saveUser(any(User.class));
        System.out.println("✅ testRegisterUser_InvalidUser_ThrowsException passed");
    }
}