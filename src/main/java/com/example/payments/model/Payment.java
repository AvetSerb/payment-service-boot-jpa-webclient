package com.example.payments.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
