package com.example.learn.login.Service;

import org.springframework.stereotype.Service;

import com.example.learn.login.Componect.User;

@Service
public interface User_Service {
    public void save(User u1);

    public void delete(User u1);

    public User getUser(User u1);

}
