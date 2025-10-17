package com.example.payments.repo;

import com.example.payments.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<Fee, Long> { }
