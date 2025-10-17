package com.example.payments.service;

import com.example.payments.domain.Fee;
import com.example.payments.repo.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaFeeStore implements FeeStore{
    private final FeeRepository repo;
    @Override
    public Fee save(Fee fee) { return repo.save(fee); }
}
