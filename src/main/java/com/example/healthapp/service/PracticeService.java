package com.example.healthapp.service;

import com.example.healthapp.model.Practice;
import com.example.healthapp.repository.PracticeRepository;
import com.example.healthapp.search.PracticeIndex;
import com.example.healthapp.search.PracticeSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracticeService {

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private PracticeSearchRepository practiceSearchRepository; // Elasticsearch Repository

    public List<Practice> getAllPractices() {
        return practiceRepository.findAll();
    }

    public Optional<Practice> getPracticeById(Long practiceId) {
        return practiceRepository.findById(practiceId);
    }

    public Practice addPractice(Practice practice) {
        Practice savedPractice = practiceRepository.save(practice);

        // Index in Elasticsearch
        PracticeIndex practiceIndex = new PracticeIndex(
                String.valueOf(savedPractice.getId()), // Convert ID to String
                savedPractice.getName(),
                savedPractice.getAddress(),
                savedPractice.getCity(),
                savedPractice.getState()
        );
        practiceSearchRepository.save(practiceIndex);

        return savedPractice;
    }

    public Practice updatePractice(Long practiceId, Practice updatedPractice) {
        return practiceRepository.findById(practiceId)
                .map(existingPractice -> {
                    existingPractice.setName(updatedPractice.getName());
                    existingPractice.setAddress(updatedPractice.getAddress());
                    existingPractice.setCity(updatedPractice.getCity());
                    existingPractice.setState(updatedPractice.getState());
                    Practice updated = practiceRepository.save(existingPractice);

                    // Update Elasticsearch index
                    PracticeIndex practiceIndex = new PracticeIndex(
                            String.valueOf(updated.getId()), // Convert ID to String
                            updated.getName(),
                            updated.getAddress(),
                            updated.getCity(),
                            updated.getState()
                    );
                    practiceSearchRepository.save(practiceIndex);

                    return updated;
                }).orElseThrow(() -> new RuntimeException("Practice not found"));
    }

    public void deletePractice(Long practiceId) {
        practiceRepository.deleteById(practiceId);
        practiceSearchRepository.deleteById(String.valueOf(practiceId)); // Fix: Convert to String
    }
}
