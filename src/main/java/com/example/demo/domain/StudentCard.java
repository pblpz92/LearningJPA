package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class StudentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String schoolIdentifier = UUID.randomUUID().toString();

    public StudentCard() {
    }

    public Long getId() {
        return id;
    }

    public String getSchoolIdentifier() {
        return schoolIdentifier;
    }

    @Override
    public String toString() {
        return String.format("%d. SC UUID is: %s", id, schoolIdentifier);
    }
}
