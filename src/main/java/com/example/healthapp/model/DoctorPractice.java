package com.example.healthapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Doctor_Practice")
public class DoctorPractice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "practice_id", nullable = false)
    private Practice practice;

    // Constructors
    public DoctorPractice() {}

    public DoctorPractice(Doctor doctor, Practice practice) {
        this.doctor = doctor;
        this.practice = practice;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Practice getPractice() { return practice; }
    public void setPractice(Practice practice) { this.practice = practice; }
}
