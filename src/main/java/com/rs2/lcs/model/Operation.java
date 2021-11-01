package com.rs2.lcs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Operation {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long CashierId;
    private LocalDateTime dateTime;
    private String operationType;
    private int pointBalance;
    private double cashSpent;
    private double cashDiscount;

    // Constructor
    public Operation() {
        dateTime = LocalDateTime.now();
    }

    // Methods
}
