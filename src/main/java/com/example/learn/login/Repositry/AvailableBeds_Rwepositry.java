package com.example.learn.login.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learn.login.Componect.Bed_Manage.Available_Bed;

@Repository
public interface AvailableBeds_Rwepositry extends JpaRepository<Available_Bed, Integer> {

}
