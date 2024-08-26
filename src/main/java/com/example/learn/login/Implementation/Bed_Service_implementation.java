package com.example.learn.login.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learn.login.Componect.Bed_Manage.Available_Bed;
import com.example.learn.login.Componect.Bed_Manage.Bed;
import com.example.learn.login.Componect.Bed_Manage.Booked_Bed;
import com.example.learn.login.Componect.Bed_Manage.Room_Type;
import com.example.learn.login.Componect.Patient.Patient;
import com.example.learn.login.Repositry.AvailableBeds_Rwepositry;
import com.example.learn.login.Repositry.Bed_Repositry;
import com.example.learn.login.Repositry.BookedBed_Repositry;
import com.example.learn.login.Service.Bed_Service;

@Service
public class Bed_Service_implementation implements Bed_Service {

    // make hte repo
    @Autowired
    private Bed_Repositry bed_Repositry;

    @Autowired
    private AvailableBeds_Rwepositry availableBeds_Rwepositry;

    @Autowired
    private BookedBed_Repositry bed_Repositry2;

    @Override
    public List<Bed> getAllBeds() {
        // TODO Auto-generated method stub
        return bed_Repositry.findAll();
    }

    @Override
    public Bed getBedById(int bed_id) {
        // TODO Auto-generated method stub
        Optional<Bed> optional = bed_Repositry.findById(bed_id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    // @Override
    // public List<Bed> getAvailableBedsByRoomType(Room_Type roomType) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'getAvailableBedsByRoomType'");
    // }

    @Override
    public boolean assignPatientToBed(Patient patient) {
        // TODO Auto-generated method stub
        if (areAllBedsFull()) {
            return false;
        }
        List<Available_Bed> list = availableBeds_Rwepositry.findAll();
        Bed bed = getBedById(list.get(0).getId());
        bed.setAssinged_patient(patient);
        availableBeds_Rwepositry.delete(list.get(0));
        Booked_Bed booked = new Booked_Bed(list.get(0).getId());
        bed_Repositry2.save(booked);

        return true;

    }

    @Override
    public boolean areAllBedsFull() {
        // TODO Auto-generated method stub
        return availableBeds_Rwepositry.findAll().isEmpty();
    }

    @Override
    public Bed saveOrUpdateBed(Bed bed) {
        // TODO Auto-generated method stub

        // naya form bhar se save kar do
        throw new UnsupportedOperationException("Unimplemented method 'saveOrUpdateBed'");
    }

    @Override
    public boolean deleteBedById(int bed_id) {
        // TODO Auto-generated method stub
        Bed bed = getBedById(bed_id);
        if (bed == null)
            return false;
        bed_Repositry.delete(bed);
        return true;
    }

    @Override
    public int getAvailableBedId() {
        // TODO Auto-generated method stub
        return availableBeds_Rwepositry.findAll().get(0).getId();

    }

    public boolean add_Bed(Bed bed) {
        if (getBedById(bed.getBed_id()) == null) {
            bed_Repositry.save(bed);
            Available_Bed available_Bed = new Available_Bed(bed.getBed_id());
            availableBeds_Rwepositry.save(available_Bed);
            return true;
        } else {
            return false;
        }
    }

}
