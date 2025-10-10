package com.example.payments.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter @Setter @NoArgsConstructor
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, precision=18, scale=2)
    private BigDecimal amountRub;

    @ManyToOne(optional=false) @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne(optional=false) @JoinColumn(name = "recipient_id")
    private User recipient;

    @Column(nullable=false)
    private LocalDate bookingDate;
}
