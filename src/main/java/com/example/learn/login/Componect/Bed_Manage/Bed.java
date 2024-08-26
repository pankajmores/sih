package com.example.learn.login.Componect.Bed_Manage;

import java.util.Date;

import com.example.learn.login.Componect.Patient.Patient;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//i donor   want  to  make  the   compopnent  
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bed {

    @Id
    private int bed_id;
    private int ward_no;
    private int bed_number;

    private String bed_stautus;
    private String room_type;

    // private java.sql.Date admission_Date;

    // this is trhe foregin Key to the Patient
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient")
    private Patient assinged_patient;

}
