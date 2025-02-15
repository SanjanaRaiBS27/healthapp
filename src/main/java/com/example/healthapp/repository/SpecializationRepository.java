package com.example.healthapp.repository;

import com.example.healthapp.model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Specialization findByNameIgnoreCase(String name); // Find specialization by name
}
