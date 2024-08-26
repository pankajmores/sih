package com.example.learn.login.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.learn.login.Componect.Bed_Manage.Bed;
import com.example.learn.login.Componect.Bed_Manage.Room_Type;
import com.example.learn.login.Componect.Patient.Patient;

@Service
public interface Bed_Service {

    List<Bed> getAllBeds();

    // Method to find a bed by its ID
    Bed getBedById(int bed_id);

    // Method to find available beds by room type
    //List<Bed> getAvailableBedsByRoomType(Room_Type roomType);

    // Method to assign a patient to a bed
    boolean assignPatientToBed(Patient patient);

    // Method to check if all beds are full
    boolean areAllBedsFull();

    // Method to save or update a bed
    Bed saveOrUpdateBed(Bed bed);

    // Method to delete a bed by its ID
    boolean deleteBedById(int bed_id);

    int getAvailableBedId();

}
