package com.rs2.lcs.dto;

public class UserIdPointDto implements UserIdPoint{
    // Attributes
    private Long userId;
    private int balance;

    // Constructor
    public UserIdPointDto(Long userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }

    // Methods
    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
