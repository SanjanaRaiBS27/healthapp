package com.example.healthapp.service;

import com.example.healthapp.model.Doctor;
import com.example.healthapp.model.Practice;
import com.example.healthapp.repository.DoctorRepository;
import com.example.healthapp.repository.PracticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class DoctorPracticeService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PracticeRepository practiceRepository;

    public void assignDoctorToPractice(Long doctorId, Long practiceId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Practice practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new RuntimeException("Practice not found"));

        doctor.getPractices().add(practice);
        doctorRepository.save(doctor);
    }

    public Set<Practice> getPracticesForDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return doctor.getPractices();
    }
}