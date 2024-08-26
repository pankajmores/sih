package com.example.learn.login.Contoller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learn.login.Componect.Patient.Patient;
import com.example.learn.login.Implementation.PatientService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/patients")
public class Test_Cont {
    @Autowired
    private PatientService patientService;

    // private Principal principal;

    @GetMapping("/all")
    public String getPatients(Model model, Principal principal) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        model.addAttribute("username", principal.getName());
        return "Patient/patients";
    }

    @GetMapping("/id")
    public String getPatientById(@RequestParam("patient_id") int patientId, Model model, Principal principal) {

        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            model.addAttribute("patient", patient);
        } else {
            model.addAttribute("errorMessage", "Patient not found. Please check the ID and try again.");
        }
        model.addAttribute("username", principal.getName());
        return "Patient/Patient_Id";
    }

    // ADD APTIENT
    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "Patient/Patient_add";
    }

    @PostMapping("/add1")
    public String addPatient(@ModelAttribute Patient patient, Model model) {

        Patient p = patientService.savePatient(patient);
        model.addAttribute("successMessage", "Patient added successfully!");
        model.addAttribute("patient", p);
        return "Patient/Patient_add"; // Redirect to the patient list page or another appropriate page
    }
    // DELETE PATIETN
    // upate pPateint

    @GetMapping("/update")
    public String update(Model model) {
        model.addAttribute("patient", new Patient());
        return "Patient/Patient_update";
    }

    @PostMapping("/update1")
    public String updatepatient(@ModelAttribute Patient patient, Model model) {

        Patient p = patientService.UpdatePatient(patient);
        if (p == null) {
            model.addAttribute("errorMessage", "Patient Cannot be   Updated!");
        }
        model.addAttribute("successMessage", "Patient added successfully!");
        model.addAttribute("patient", p);
        return "Patient/Patient_update"; // Redirect to the patient list page or another appropriate page
    }

    @GetMapping("/delete")
    public String delete(Model model) {

        return "Patient/Patient_delete";
    }

    @PostMapping("/delete1")
    public String deletePatient(@RequestParam("patient_id") int patientId, Model model) {
        boolean isDeleted = patientService.deletePatientById(patientId); // Adjust service call as needed

        if (isDeleted) {
            model.addAttribute("successMessage", "Patient with ID " + patientId + " has been successfully deleted.");
        } else {
            model.addAttribute("errorMessage", "Patient with ID " + patientId + " not found or could not be deleted.");
        }

        return "Patient/Patient_delete";
    }

}
