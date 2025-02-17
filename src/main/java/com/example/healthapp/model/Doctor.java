package com.example.healthapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String qualifications;
    private int experienceYears;
    private String bio;
    @Column(unique = true)

    @ManyToMany(mappedBy = "doctors")
    @JsonIgnore
    private Set<Practice> practices;

    @ManyToMany
    @JoinTable(
            name = "doctor_specializations",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )

    private Set<Specialization> specializations;

    // Default constructor
    public Doctor() {}

    // Constructor
    public Doctor(String name, String qualifications, int experienceYears, String bio) {
        this.name = name;
        this.qualifications = qualifications;
        this.experienceYears = experienceYears;
        this.bio = bio;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<Practice> getPractices() {
        return practices;
    }

    public void setPractices(Set<Practice> practices) {
        this.practices = practices;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }
}