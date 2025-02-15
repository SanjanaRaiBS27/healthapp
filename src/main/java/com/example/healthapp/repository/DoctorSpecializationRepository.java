package com.example.healthapp.repository;

import com.example.healthapp.model.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, Long> {
    List<DoctorSpecialization> findByDoctorId(Long doctorId); // Get specializations for a doctor

    List<DoctorSpecialization> findBySpecializationId(Long specializationId); // Get doctors for a specialization
}
