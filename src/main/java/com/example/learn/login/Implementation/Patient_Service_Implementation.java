package com.example.learn.login.Implementation;

import com.example.learn.login.Componect.Patient.Patient;
import com.example.learn.login.Repositry.Patient_Repositry;
import com.example.learn.login.Service.Patient_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Patient_Service_Implementation implements Patient_service {

    @Autowired
    private Patient_Repositry patientRepository;

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(int id) {
        Optional<Patient> opt = patientRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public boolean deletePatient(int id) {
        Patient p = getPatientById(id);
        if (p == null)
            return false;
        patientRepository.deleteById(p.getPatient_id());
        return true;
    }

    @Override
    public Optional<Patient> getPatientId(int id) {
        // TODO Auto-generated method stub
        return patientRepository.findById(id);
    }

    @Override
    public Patient update(Patient patient) {
        // TODO Auto-generated method stub
        Optional<Patient> optional = patientRepository.findById(patient.getPatient_id());
        if (optional.isPresent()) {
            patientRepository.save(optional.get());
            return optional.get();
        } else {
            return null;
        }
    }
}
