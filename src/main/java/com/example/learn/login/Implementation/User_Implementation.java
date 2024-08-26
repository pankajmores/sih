package com.example.learn.login.Implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.learn.login.Componect.User;
import com.example.learn.login.Componect.User_Model;
import com.example.learn.login.Repositry.User_Repositry;
import com.example.learn.login.Service.User_Service;

@Service
public class User_Implementation implements User_Service {

    @Autowired
    private User_Repositry repositry;

    @Autowired
    private PasswordEncoder passwordencoder;

    @Override
    public void save(User u1) {
        String pass = "";
        System.out.println("Saving  Time  the   Passwrod  " + pass);
        u1.setPassword(pass);
        repositry.save(u1);
    }

    public void save1(User_Model user_Model) {
        User u1 = new User();
        u1.setUsername(user_Model.getUsername());
        u1.setPassword(passwordencoder.encode(user_Model.getPassword()));
        repositry.save(u1);
    }

    @Override
    public void delete(User u1) {
        repositry.delete(u1);
    }

    @Override
    public User getUser(User u1) {

        return null;
    }

    public User getUserByUsername(String username) {
        Optional<User> optional = repositry.findById(username);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public boolean getUserByUsernameBoolean(String username) {
        Optional<User> optional = repositry.findById(username);
        return optional.isPresent();
    }

    public boolean findModel(User_Model model) {
        Optional<User> optional = repositry.findById(model.getUsername());
        if (optional.isPresent()) {
            boolean found = passwordencoder.matches(model.getPassword(), optional.get().getPassword());

            System.out.println("Raw password: " + model.getPassword());
            System.out.println("Encoded one " + passwordencoder.encode(model.getPassword()));
            System.out.println("Stored hash: " + optional.get().getPassword());
            // System.out.println("Password match: " + isMatch);

            System.out.println(found);
            return found;
        }
        return false;
    }

}
