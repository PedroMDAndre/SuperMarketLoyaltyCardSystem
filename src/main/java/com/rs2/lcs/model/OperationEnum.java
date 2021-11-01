package com.rs2.lcs.model;

public enum OperationEnum {
    PURCHASE("Purchase"),
    REDEEM("Redeem");

    // Attributes
    private final String description;

    // Constructor
    OperationEnum(String description) {
        this.description = description;
    }

    // Methods
    public String getDescription() {
        return this.description;
    }

    public static OperationEnum getOperation(String description) {
        if (PURCHASE.getDescription().equals(description)) {
            return PURCHASE;
        }
        return REDEEM;
    }
}
