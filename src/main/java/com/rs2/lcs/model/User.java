package com.rs2.lcs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    // Attributes
    @Id
    Long id;

    String name;

    String surname;

    @Column(unique = true)
    Long mobileNumber;

    @Column(unique = true)
    Long idCardNumber;

    // Constructor
    public User() {

    }
}
