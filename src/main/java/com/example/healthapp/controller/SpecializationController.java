package com.example.healthapp.controller;

import com.example.healthapp.model.Specialization;
import com.example.healthapp.search.SpecializationIndex;
import com.example.healthapp.service.SpecializationService;
import com.example.healthapp.search.SpecializationSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/specializations")
@CrossOrigin(origins = "http://localhost:3000")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private SpecializationSearchRepository specializationSearchRepository;

    @GetMapping
    public List<Specialization> getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }

    @GetMapping("/{id}")
    public Optional<Specialization> getSpecializationById(@PathVariable Long id) {
        return specializationService.getSpecializationById(id);
    }

    @PostMapping
    public Specialization addSpecialization(@RequestBody Specialization specialization) {
        return specializationService.addSpecialization(specialization);
    }

    @PutMapping("/{id}")
    public Specialization updateSpecialization(@PathVariable Long id, @RequestBody Specialization updatedSpecialization) {
        return specializationService.updateSpecialization(id, updatedSpecialization);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecialization(@PathVariable Long id) {
        specializationService.deleteSpecialization(id);
    }

    @GetMapping("/search")
    public List<SpecializationIndex> searchSpecializations(@RequestParam String query) {
        return specializationSearchRepository.findByNameContainingIgnoreCase(query);
    }


}