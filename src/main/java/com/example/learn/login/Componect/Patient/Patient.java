package com.example.learn.login.Componect.Patient;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient implements UserDetails {

    @Id
    private int patient_id;

    private String patient_name;// full name t be enter
    // private Date DoB;// th
    private String patient_gender;

    private int phone_number;
    private String address;
    private String email;

    // THE FEATURE OF TEH MEDICAL_HISTORY
    // THE FEATURE OF THE INSURANCE ID

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return patient_name;

    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;

    }

}
