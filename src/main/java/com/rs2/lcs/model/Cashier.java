package com.rs2.lcs.model;

import javax.persistence.*;

@Entity
public class Cashier {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    // Constructor

    // Methods
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
