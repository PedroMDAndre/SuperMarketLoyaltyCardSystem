package com.rs2.lcs.model;

import javax.persistence.*;

@Entity
public class User {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true)
    private Long mobileNumber;

    @Column(unique = true)
    private Long idCardNumber;

    // Constructor
    public User() {

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
