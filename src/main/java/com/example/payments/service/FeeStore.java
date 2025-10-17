package com.example.payments.service;

import com.example.payments.domain.Fee;

public interface FeeStore {
    Fee save(Fee fee);
}
