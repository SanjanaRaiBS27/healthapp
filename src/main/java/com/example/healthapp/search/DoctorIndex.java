package com.example.healthapp.search;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;

@Document(indexName = "doctors")
public class DoctorIndex {

    @Id
    private String id;
    private String name;
    private String qualifications;
    private int experienceYears;  // Ensure this matches the service class
    private String bio;

    public DoctorIndex(String id, String name, String qualifications, int experienceYears, String bio) {
        this.id = id;
        this.name = name;
        this.qualifications = qualifications;
        this.experienceYears = experienceYears;
        this.bio = bio;
    }

    // Getters and Setters
}
