package com.example.healthapp.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "practices")
public class PracticeIndex {

    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private String state;

    public PracticeIndex(String id, String name, String address, String city, String state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    // Getters and Setters
}
