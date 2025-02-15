package com.example.healthapp.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "specialization_index")
public class SpecializationIndex {

    @Id
    private String id;
    private String name;

    public SpecializationIndex(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters
}

