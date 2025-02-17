package com.example.healthapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Practice")
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String city;
    private String state;


    @ManyToMany
    @JoinTable(
            name = "practice_specializations",
            joinColumns = @JoinColumn(name = "practice_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<Specialization> specializations;

    @ManyToMany

    @JoinTable(
            name = "doctor_practice",
            joinColumns = @JoinColumn(name = "practice_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctors;

    // Default constructor
    public Practice() {}

    // Constructor
    public Practice(String name, String address, String city, String state) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.specializations = new HashSet<>(); // Initialize specializations
        this.doctors = new HashSet<>(); // Initialize doctors
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}