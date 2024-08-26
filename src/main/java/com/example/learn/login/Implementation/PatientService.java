package com.example.learn.login.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.learn.login.Componect.Patient.Patient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PatientService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Patient_Service_Implementation patient_Service_Implementation;

    private static final String API_URL = "http://localhost:8080/api/patients";

    public List<Patient> getAllPatients() {
        StringBuilder sb = new StringBuilder(API_URL);
        // sb.append("/all");
        Patient[] patientsArray = restTemplate.getForObject(sb.toString(), Patient[].class);

        return Arrays.asList(patientsArray);
    }

    public Patient getPatientById(int id) {
        return patient_Service_Implementation.getPatientById(id);

    }

    public Patient savePatient(Patient patient) {
        return patient_Service_Implementation.savePatient(patient);
    }

    public Patient UpdatePatient(Patient patient) {
        return patient_Service_Implementation.update(patient);
    }

    public boolean deletePatientById(int patientId) {
        return patient_Service_Implementation.deletePatient(patientId);
    }
}