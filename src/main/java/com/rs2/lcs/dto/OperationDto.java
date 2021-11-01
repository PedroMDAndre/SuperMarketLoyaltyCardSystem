package com.rs2.lcs.dto;

import java.time.LocalDateTime;

public class OperationDto {
    // Attributes
    private Long id;
    private Long userId;
    private Long cashierId;
    private LocalDateTime dateTime;
    private String operationType;
    private int pointBalance;
    private double cashSpent;
    private double cashDiscount;
    private boolean deliveredWaterPacket;

    // Methods
    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setPointBalance(int pointBalance) {
        this.pointBalance = pointBalance;
    }

    public void setCashSpent(double cashSpent) {
        this.cashSpent = cashSpent;
    }

    public void setCashDiscount(double cashDiscount) {
        this.cashDiscount = cashDiscount;
    }

    public void setDeliveredWaterPacket(boolean deliveredWaterPacket) {
        this.deliveredWaterPacket = deliveredWaterPacket;
    }

    // Methods
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getOperationType() {
        return operationType;
    }

    public int getPointBalance() {
        return pointBalance;
    }

    public double getCashSpent() {
        return cashSpent;
    }

    public double getCashDiscount() {
        return cashDiscount;
    }

    public boolean isDeliveredWaterPacket() {
        return deliveredWaterPacket;
    }
}
