package com.example.payments.service.impl;

import com.example.payments.model.User;
import com.example.payments.repo.UserRepository;
import com.example.payments.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id)));
    }
}
