package com.example.learn.login.Repositry;

import com.example.learn.login.Componect.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Patient_Repositry extends JpaRepository<Patient, Integer> {
    // You can define custom query methods if needed
}
