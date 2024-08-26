package com.example.learn.login.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learn.login.Componect.Bed_Manage.Booked_Bed;

@Repository
public interface BookedBed_Repositry extends JpaRepository<Booked_Bed, Integer> {

}
