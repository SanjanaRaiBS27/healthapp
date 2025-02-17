package com.example.healthapp.controller;

import com.example.healthapp.model.Doctor;
import com.example.healthapp.search.DoctorIndex;
import com.example.healthapp.service.DoctorService;
import com.example.healthapp.search.DoctorSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorSearchRepository doctorSearchRepository;

    // Get all doctors
   @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        // Log the response to ensure it's valid
        System.out.println(doctors);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    // Search doctors by name (using the Doctor entity)

    @GetMapping("doctor-name/{name}")
    public List<Doctor> getDoctorByName(@PathVariable String name) {
        return doctorService.getDoctorByName(name);
    }



    // Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        if (doctor.isPresent()) {
            return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if not found
        }
    }

    // Add a new doctor
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor createdDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED); // 201 for created resource
    }

    // Update an existing doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        Optional<Doctor> existingDoctor = doctorService.getDoctorById(id);
        if (existingDoctor.isPresent()) {
            updatedDoctor.setId(id); // Ensure ID is updated
            Doctor doctor = doctorService.updateDoctor(id, updatedDoctor);
            return new ResponseEntity<>(doctor, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if not found
        }
    }

    // Delete a doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        Optional<Doctor> existingDoctor = doctorService.getDoctorById(id);
        if (existingDoctor.isPresent()) {
            doctorService.deleteDoctor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if not found
        }
    }

    // Search doctors by name
    @GetMapping("/search")
    public ResponseEntity<List<DoctorIndex>> searchDoctors(@RequestParam String query) {
        List<DoctorIndex> searchResults = doctorSearchRepository.findByNameContainingIgnoreCase(query);
        if (searchResults.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if no doctors found
        } else {
            return new ResponseEntity<>(searchResults, HttpStatus.OK); // 200 OK
        }
    }
}
