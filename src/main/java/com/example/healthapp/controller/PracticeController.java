package com.example.healthapp.controller;

import com.example.healthapp.model.Practice;
import com.example.healthapp.search.PracticeIndex;
import com.example.healthapp.service.PracticeService;
import com.example.healthapp.search.PracticeSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/practices")
@CrossOrigin(origins = "http://localhost:3000")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private PracticeSearchRepository practiceSearchRepository;

    @GetMapping
    public List<Practice> getAllPractices() {
        return practiceService.getAllPractices();
    }

    @GetMapping("/{id}")
    public Optional<Practice> getPracticeById(@PathVariable Long id) {
        return practiceService.getPracticeById(id);
    }

    @PostMapping
    public Practice addPractice(@RequestBody Practice practice) {
        return practiceService.addPractice(practice);
    }

    @PutMapping("/{id}")
    public Practice updatePractice(@PathVariable Long id, @RequestBody Practice updatedPractice) {
        return practiceService.updatePractice(id, updatedPractice);
    }

    @DeleteMapping("/{id}")
    public void deletePractice(@PathVariable Long id) {
        practiceService.deletePractice(id);
    }

    @GetMapping("/search")
    public List<PracticeIndex> searchPractices(@RequestParam String query) {
        return practiceSearchRepository.findByNameContainingIgnoreCase(query);
    }


}
