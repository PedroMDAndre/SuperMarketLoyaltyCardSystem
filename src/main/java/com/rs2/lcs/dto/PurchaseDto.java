package com.rs2.lcs.dto;


import javax.validation.constraints.NotBlank;

public class PurchaseDto {
    // Attributes
    @NotBlank
    private Long userId;

    @NotBlank
    private Long cashierId;

    private double cashSpent;

    // Constructor
    public PurchaseDto(Long userId, Long cashierId, double cashSpent) {
        this.userId = userId;
        this.cashierId = cashierId;
        this.cashSpent = cashSpent;
    }

    // Methods
    public Long getUserId() {
        return userId;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public double getCashSpent() {
        return cashSpent;
    }
}
