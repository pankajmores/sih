package com.example.learn.login.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.learn.login.Componect.Patient.Patient;

@Service
public interface Patient_service {
    List<Patient> getAllPatients();

    Patient getPatientById(int id);

    Patient savePatient(Patient patient);

    boolean deletePatient(int id);

    Optional<Patient> getPatientId(int id);

    Patient update(Patient patient);

}
