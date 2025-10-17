package com.example.payments.service.impl;

import com.example.payments.model.Fee;
import com.example.payments.repo.FeeRepository;
import com.example.payments.service.FeeStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeeStoreImpl implements FeeStore {
    private final FeeRepository repo;

    @Override
    public Fee save(Fee fee) {
        return repo.save(fee);
    }
}
