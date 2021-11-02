package com.rs2.lcs.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    @NotBlank
    private Long mobileNumber;

    @Column(unique = true)
    @NotBlank
    private Long idCardNumber;

    // Constructor
    public User() {

    }

    public User(String name, String surname, Long mobileNumber, Long idCardNumber) {
        this.name = name;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
        this.idCardNumber = idCardNumber;
    }

    // Methods
    public Long getId() {
        return id;
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
