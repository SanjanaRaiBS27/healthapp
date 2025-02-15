package com.example.healthapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "practice_specializations")
public class PracticeSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "practice_id", nullable = false)
    private Practice practice;

    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

    // Constructors
    public PracticeSpecialization() {}

    public PracticeSpecialization(Practice practice, Specialization specialization) {
        this.practice = practice;
        this.specialization = specialization;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Practice getPractice() { return practice; }
    public void setPractice(Practice practice) { this.practice = practice; }
    public Specialization getSpecialization() { return specialization; }
    public void setSpecialization(Specialization specialization) { this.specialization = specialization; }
}
