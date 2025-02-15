package com.example.healthapp.service;

import com.example.healthapp.model.Practice;
import com.example.healthapp.model.Specialization;
import com.example.healthapp.repository.PracticeRepository;
import com.example.healthapp.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class PracticeSpecializationService {

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    public void assignSpecializationToPractice(Long practiceId, Long specializationId) {
        Practice practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new RuntimeException("Practice not found"));
        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        practice.getSpecializations().add(specialization);
        practiceRepository.save(practice);
    }

    public Set<Specialization> getSpecializationsForPractice(Long practiceId) {
        Practice practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new RuntimeException("Practice not found"));
        return practice.getSpecializations();
    }
}
