package com.example.healthapp.repository;

import com.example.healthapp.model.PracticeSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeSpecializationRepository extends JpaRepository<PracticeSpecialization, Long> {
    List<PracticeSpecialization> findByPracticeId(Long practiceId); // Get specializations available in a practice

    List<PracticeSpecialization> findBySpecializationId(Long specializationId); // Get practices offering a specialization
}
