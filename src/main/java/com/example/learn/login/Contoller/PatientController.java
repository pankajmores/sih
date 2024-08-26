package com.example.learn.login.Contoller;

import com.example.learn.login.Componect.Patient.Patient;
import com.example.learn.login.Implementation.Patient_Service_Implementation;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private Patient_Service_Implementation patientService;

    // Get all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a specific patient by ID
    @GetMapping("/id")
    public ResponseEntity<Patient> getPatientById(@RequestBody int id) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new patient
    @PostMapping("/add")
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return patient;
    }

    // Update an existing patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @Valid @RequestBody Patient patient) {
        Optional<Patient> existingPatient = patientService.getPatientId(id);
        if (existingPatient.isPresent()) {
            patient.setPatient_id(id);
            Patient updatedPatient = patientService.savePatient(patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        Optional<Patient> existingPatient = patientService.getPatientId(id);
        if (existingPatient.isPresent()) {
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
