package com.example.healthapp.service;

import com.example.healthapp.model.Specialization;
import com.example.healthapp.repository.SpecializationRepository;
import com.example.healthapp.search.SpecializationIndex;
import com.example.healthapp.search.SpecializationSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private SpecializationSearchRepository specializationSearchRepository; // Elasticsearch repository

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public Optional<Specialization> getSpecializationById(Long specializationId) {
        return specializationRepository.findById(specializationId);
    }

    public Specialization addSpecialization(Specialization specialization) {
        Specialization savedSpecialization = specializationRepository.save(specialization);

        // Indexing in Elasticsearch
        SpecializationIndex specializationIndex = new SpecializationIndex(
                savedSpecialization.getId().toString(), // Convert ID to String
                savedSpecialization.getName()
        );
        specializationSearchRepository.save(specializationIndex);

        return savedSpecialization;
    }

    public Specialization updateSpecialization(Long specializationId, Specialization updatedSpecialization) {
        return specializationRepository.findById(specializationId)
                .map(existingSpecialization -> {
                    existingSpecialization.setName(updatedSpecialization.getName());
                    existingSpecialization.setDescription(updatedSpecialization.getDescription());
                    Specialization updated = specializationRepository.save(existingSpecialization);

                    // Update Elasticsearch index
                    SpecializationIndex specializationIndex = new SpecializationIndex(
                            updated.getId().toString(),
                            updated.getName()
                    );
                    specializationSearchRepository.save(specializationIndex);

                    return updated;
                }).orElseThrow(() -> new RuntimeException("Specialization not found"));
    }

    public void deleteSpecialization(Long specializationId) {
        specializationRepository.deleteById(specializationId);
        specializationSearchRepository.deleteById(specializationId.toString()); // Convert ID to String for Elasticsearch
    }
}
