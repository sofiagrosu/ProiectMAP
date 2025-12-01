package com.example.flight_management_system;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllErrors(HttpServletRequest req, Exception ex) {

        System.err.println("--- GLOBAL EXCEPTION CAUGHT ---");
        ex.printStackTrace();

        ModelAndView mav = new ModelAndView();
        mav.addObject("timestamp", new java.util.Date());
        mav.addObject("url", req.getRequestURL());

        String userMessage = "An unexpected error occurred. Please check the data and try again.";

        if (ex instanceof IllegalArgumentException) {
            userMessage = "Input Error: " + ex.getMessage();
        } else if (ex instanceof DataIntegrityViolationException) {
            // Catches Foreign Key violations and other DB constraints (Requirement: No operation that violates existing relationships)
            userMessage = "Data integrity error: Cannot delete or save due to related records. Check if the entity is used by other records (e.g., cannot delete an airplane with active flights).";
        }

        mav.addObject("errorMessage", userMessage);
        mav.setViewName("error");

        return mav;
    }
}