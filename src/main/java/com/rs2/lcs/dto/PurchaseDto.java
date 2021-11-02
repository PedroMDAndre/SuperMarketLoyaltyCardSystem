package com.rs2.lcs.dto;


import javax.validation.constraints.NotBlank;

public class PurchaseDto {
    // Attributes
    @NotBlank
    private Long userId;

    @NotBlank
    private Long cashierId;

    private double cashSpent;

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
