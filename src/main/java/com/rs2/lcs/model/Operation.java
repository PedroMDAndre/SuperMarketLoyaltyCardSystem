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
    private Long pointBalance;
    private Double cashSpent;
    private Double cashDiscount;
    private Boolean deliveredWaterPacket;

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

    public Long getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(Long pointBalance) {
        this.pointBalance = pointBalance;
    }

    public Double getCashSpent() {
        return cashSpent;
    }

    public void setCashSpent(double cashSpent) {
        this.cashSpent = cashSpent;
    }

    public Double getCashDiscount() {
        return cashDiscount;
    }

    public void setCashDiscount(double cashDiscount) {
        this.cashDiscount = cashDiscount;
    }
}
