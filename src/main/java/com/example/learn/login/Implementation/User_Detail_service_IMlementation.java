package com.example.learn.login.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.learn.login.Componect.User;
import com.example.learn.login.Service.User_Detail_Sevice;

@Service
public class User_Detail_service_IMlementation implements User_Detail_Sevice {

    @Autowired

    private User_Implementation user_Implementation;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return user_Implementation.getUserByUsername(username);
        // if (u1 == null) {
        // System.out.println("the User Name Search Not fOUND - > " + username);
        // try {
        // throw new Exception("Not Founf in teh db");
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }
        // return u1;

    }

}
