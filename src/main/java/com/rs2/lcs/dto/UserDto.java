package com.rs2.lcs.dto;

public class UserDto {
    // Attributes
    private Long id;
    private String name;
    private String surname;
    private Long mobileNumber;
    private Long idCardNumber;

    // Methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public Long getIdCardNumber() {
        return idCardNumber;
    }
}
