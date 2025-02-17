package com.example.healthapp.service;

import com.example.healthapp.model.Doctor;
import com.example.healthapp.model.Specialization;
import com.example.healthapp.repository.DoctorRepository;
import com.example.healthapp.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class DoctorSpecializationService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    public void assignSpecializationToDoctor(Long doctorId, Long specializationId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        doctor.getSpecializations().add(specialization);
        doctorRepository.save(doctor);
    }

    public Set<Specialization> getSpecializationsForDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctor.getSpecializations();
    }
}