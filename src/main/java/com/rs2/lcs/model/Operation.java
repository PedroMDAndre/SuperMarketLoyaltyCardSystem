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
    private Long cashierId;
    private LocalDateTime dateTime;
    private String operationType;
    private int pointBalance = 0;
    private double cashSpent = 0;
    private double cashDiscount = 0;
    private boolean deliveredWaterPacket = false;

    // Constructor
    public Operation(Long userId, Long cashierId, String operationType) {
        this.userId = userId;
        this.cashierId = cashierId;
        this.dateTime = LocalDateTime.now();
        this.operationType = operationType;
    }

    // Methods
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCashierId() {
        return this.cashierId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public int getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(int pointBalance) {
        this.pointBalance = pointBalance;
    }

    public double getCashSpent() {
        return cashSpent;
    }

    public void setCashSpent(double cashSpent) {
        this.cashSpent = cashSpent;
    }

    public double getCashDiscount() {
        return cashDiscount;
    }

    public void setCashDiscount(double cashDiscount) {
        this.cashDiscount = cashDiscount;
    }
}
