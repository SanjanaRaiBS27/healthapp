package com.example.healthapp.repository;

import com.example.healthapp.model.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    List<Practice> findByNameContainingIgnoreCase(String name); // Search practices by name (case-insensitive)

    List<Practice> findBySpecializations_Id(Long specializationId); // Find practices offering a specific specialization
}
