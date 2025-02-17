package com.example.healthapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;

@Entity
@Table(name = "Specializations")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "specializations")
    @JsonIgnore

    private Set<Doctor> doctors;

    @ManyToMany(mappedBy = "specializations")
    @JsonIgnore
    private Set<Practice> practices;

    // Default constructor
    public Specialization() {}

    // Constructor
    public Specialization(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Set<Practice> getPractices() {
        return practices;
    }

    public void setPractices(Set<Practice> practices) {
        this.practices = practices;
    }
}