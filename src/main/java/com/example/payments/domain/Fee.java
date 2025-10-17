package com.example.payments.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "fees")
@Getter @Setter @NoArgsConstructor
@SuperBuilder
public class Fee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"value\"", nullable=false, precision=18, scale=2)
    private BigDecimal value;

    @ManyToOne(optional=false) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional=false) @JoinColumn(name = "payment_id")
    private Payment payment;
}
