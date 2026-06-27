package com.exercises.service;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserById(int id);
    void saveUser(User user);
}