package com.example.healthapp.service;

import com.example.healthapp.model.Doctor;
import com.example.healthapp.repository.DoctorRepository;
import com.example.healthapp.search.DoctorIndex;
import com.example.healthapp.search.DoctorSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorSearchRepository doctorSearchRepository; // Elasticsearch Repository

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    public Doctor addDoctor(Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);

        // Indexing the doctor in Elasticsearch
        DoctorIndex doctorIndex = new DoctorIndex(
                savedDoctor.getId().toString(),
                savedDoctor.getName(),
                savedDoctor.getQualifications(),
                savedDoctor.getExperienceYears(),
                savedDoctor.getBio()
        );
        doctorSearchRepository.save(doctorIndex);

        return savedDoctor;
    }

    public Doctor updateDoctor(Long doctorId, Doctor updatedDoctor) {
        return doctorRepository.findById(doctorId)
                .map(existingDoctor -> {
                    existingDoctor.setName(updatedDoctor.getName());
                    existingDoctor.setQualifications(updatedDoctor.getQualifications());
                    existingDoctor.setExperienceYears(updatedDoctor.getExperienceYears());
                    existingDoctor.setBio(updatedDoctor.getBio());
                    Doctor updated = doctorRepository.save(existingDoctor);

                    // Update the Elasticsearch index
                    DoctorIndex doctorIndex = new DoctorIndex(
                            updated.getId().toString(),
                            updated.getName(),
                            updated.getQualifications(),
                            updated.getExperienceYears(),
                            updated.getBio()
                    );
                    doctorSearchRepository.save(doctorIndex);

                    return updated;
                }).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
        doctorSearchRepository.deleteById(id.toString()); // Remove from Elasticsearch
    }
}
