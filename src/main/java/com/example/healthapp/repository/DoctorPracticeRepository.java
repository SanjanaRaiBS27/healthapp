package com.example.healthapp.repository;

import com.example.healthapp.model.DoctorPractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorPracticeRepository extends JpaRepository<DoctorPractice, Long> {
    List<DoctorPractice> findByDoctorId(Long doctorId); // Get practices where a doctor works

    List<DoctorPractice> findByPracticeId(Long practiceId); // Get doctors working at a specific practice
}
