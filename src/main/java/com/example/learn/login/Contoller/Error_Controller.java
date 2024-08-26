package com.example.learn.login.Contoller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class Error_Controller implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // You can add logic to handle different error types here
        return "error"; // Return the name of your error view
    }
    // @ExceptionHandler(Exception.class)
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public String handleException(Exception e, Model model) {
    // model.addAttribute("error", "An unexpected error occurred: " +
    // e.getMessage());
    // return "error"; // return the name of your error page template
    // }
}
