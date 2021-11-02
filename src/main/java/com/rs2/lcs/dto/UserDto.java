package com.rs2.lcs.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {
    // Attributes
    private String name;
    private String surname;

    @NotBlank
    private Long mobileNumber;

    @NotBlank
    private Long idCardNumber;

    // Methods
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
