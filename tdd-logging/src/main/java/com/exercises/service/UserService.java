package com.exercises.service;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(int id) {
        Optional<User> user = userRepository.findUserById(id);
        return user.orElse(null);
    }

    public void registerUser(User user) {
        if (user == null || user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Invalid user data");
        }
        userRepository.saveUser(user);
    }
}