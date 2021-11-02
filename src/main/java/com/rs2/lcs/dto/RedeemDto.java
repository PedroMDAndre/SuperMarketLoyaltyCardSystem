package com.rs2.lcs.dto;

import javax.validation.constraints.NotBlank;

public class RedeemDto {
    // Attributes
    @NotBlank
    private Long userId;

    @NotBlank
    private Long cashierId;

    private boolean deliveredWaterPacket;

    // Constructor
    public RedeemDto(Long userId, Long cashierId, boolean deliveredWaterPacket) {
        this.userId = userId;
        this.cashierId = cashierId;
        this.deliveredWaterPacket = deliveredWaterPacket;
    }

    // Methods
    public Long getUserId() {
        return userId;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public boolean isDeliveredWaterPacket() {
        return deliveredWaterPacket;
    }
}

