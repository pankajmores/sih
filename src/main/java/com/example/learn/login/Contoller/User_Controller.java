package com.example.learn.login.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.learn.login.Componect.User;
import com.example.learn.login.Componect.User_Model;
import com.example.learn.login.Implementation.User_Implementation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class User_Controller {

    @Autowired
    private User_Implementation user_Implementation;

    @GetMapping("/")
    public String getMethodName() {
        return "redirect:home";
    }

    @GetMapping("/home")
    public String Home(org.springframework.ui.Model model) {
        model.addAttribute("message", "WELCOME  TO  THE   HOME   PAGE");
        return "home1";
    }

    @GetMapping("/signup")
    public String signup(org.springframework.ui.Model model) {
        model.addAttribute("user_form", new User_Model());
        return "Signup";

    }

    @PostMapping("/signup/v1")
    public String signupService(@ModelAttribute User_Model user_Model, Model model) {

        if (user_Implementation.getUserByUsernameBoolean(user_Model.getUsername())) {
            model.addAttribute("errorMessage", "Username already exists. Please choose a different username.");
            return "Signup";
        }

        // Save the user
        user_Implementation.save1(user_Model);

        model.addAttribute("successMessage", "You have successfully signed up!");
        return "Signup";
    }

    @RequestMapping("/login")
    public String login(Model m1) {
        // TODO: process POST request

        return "login1";
    }

    @PostMapping("/login/v1")
    public String loginService(@RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {
        User_Model userModel = new User_Model();
        userModel.setUsername(username);
        userModel.setPassword(password);
        System.out.println("username " + username + " Password " + password);
        if (user_Implementation.findModel(userModel)) {
            System.out.println("FINDING");
            return "redirect:/welcome";
        } else {
            System.out.println("noT ABLE TO FIND");
            model.addAttribute("error", "Invalid Username Or Password");
            return "redirect:login";
        }
    }

    @GetMapping("/welcome")
    public String Welcome() {
        return "Welcome";
    }

    @GetMapping("/Patient")
    public String Patient_Home() {
        return "Patient/Patient_Home";
    }

}
