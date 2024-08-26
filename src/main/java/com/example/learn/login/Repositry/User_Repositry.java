package com.example.learn.login.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learn.login.Componect.User;
import java.util.List;

@Repository
public interface User_Repositry extends JpaRepository<User, String> {
    // the methofs to be implemnts

}
