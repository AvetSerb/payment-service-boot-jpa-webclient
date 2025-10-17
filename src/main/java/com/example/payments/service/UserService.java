package com.example.payments.service;

import com.example.payments.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
}
