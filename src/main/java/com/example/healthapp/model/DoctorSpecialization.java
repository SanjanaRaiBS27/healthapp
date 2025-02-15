package com.example.healthapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Doctor_Specializations")
public class DoctorSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

    // Constructors
    public DoctorSpecialization() {}

    public DoctorSpecialization(Doctor doctor, Specialization specialization) {
        this.doctor = doctor;
        this.specialization = specialization;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Specialization getSpecialization() { return specialization; }
    public void setSpecialization(Specialization specialization) { this.specialization = specialization; }
}
