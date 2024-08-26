package com.example.learn.login.Contoller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.learn.login.Componect.Bed_Manage.Bed;
import com.example.learn.login.Componect.Patient.Patient;
import com.example.learn.login.Implementation.Bed_Service_implementation;
import com.example.learn.login.Implementation.Patient_Service_Implementation;
import com.example.learn.login.Repositry.AvailableBeds_Rwepositry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/beds")
public class Bed_Contoller {

    @Autowired
    private Bed_Service_implementation bed_Service_implementation;
    @Autowired
    private Patient_Service_Implementation patient_Service_Implementation;

    @GetMapping
    public String Bed_Home() {
        return "Bed/Bed_Home";
    }

    @GetMapping("/see/all")
    public String getAllBeds(Model model, Principal principal) {
        List<Bed> beds = bed_Service_implementation.getAllBeds();
        model.addAttribute("beds", beds);
        model.addAttribute("username", principal.getName());
        return "Bed/Bed_All"; // Return the view name for displaying the beds
    }

    // Display a form to add a new bed
    @GetMapping("/add")
    public String showAddBedForm(Model model, Principal principal) {
        Bed bed = new Bed();
        model.addAttribute("bed", bed);
        model.addAttribute("username", principal.getName());
        return "Bed/Bed_Add"; // Return the view name for adding a new bed
    }

    // Handle form submission for adding a new bed
    @PostMapping("/add1")
    public String addBed(@ModelAttribute Bed bed, Model model) {
        // model.addAttribute("Assinged_Patient",
        // bed.getAssinged_patient().getPatient_id());

        if (bed_Service_implementation.add_Bed(bed)) {
            model.addAttribute("successMessage", "Bed added successfully!");

        } else {
            model.addAttribute("errorMessage", "Bed Cannot  be  added!");
        }

        return "Bed/Bed_Add"; // Redirect to the list of beds after adding
    }

    // Display a form to update an existing bed
    /*
     * @GetMapping("/update/{bed_id}")
     * public String showUpdateBedForm(@PathVariable int bed_id, Model model) {
     * Bed bed = bed_Service_implementation.getBedById(bed_id);
     * model.addAttribute("bed", bed);
     * return "bed_update"; // Return the view name for updating a bed
     * }
     * 
     * // Handle form submission for updating an existing bed
     * 
     * @PostMapping("/update/{bed_id}")
     * public String updateBed(@PathVariable int bed_id, @ModelAttribute("bed") Bed
     * bed) {
     * bed.setBed_id(bed_id); // Ensure the ID is set correctly
     * bed_Service_implementation.updateBed(bed);
     * return "redirect:/beds"; // Redirect to the list of beds after updating
     * }
     * 
     * // Handle the deletion of a
     */

    @GetMapping("/delete")
    public String delete(Model model) {

        return "Bed/Bed_Delete";
    }

    @PostMapping("/delete1")
    public String deletePatient(@RequestParam int bed_id, Model model) {
        boolean isDeleted = bed_Service_implementation.deleteBedById(bed_id);

        if (isDeleted) {
            model.addAttribute("successMessage", "Bed with ID " + bed_id + " has been successfully deleted.");
        } else {
            model.addAttribute("errorMessage", "Bed with ID " + bed_id + " not found or could not be deleted.");
        }

        return "Bed/Bed_Delete";
    }

    @GetMapping("/allocate")
    public String Allocate() {
        return "Bed/Bed_Allocate";
    }

    @PostMapping("/verify")
    public String verifyPatient(@RequestParam("patient_id") int patientId, Model model, Principal principal) {
        Patient patient = patient_Service_Implementation.getPatientById(patientId);
        model.addAttribute("username", principal.getName());
        if (patient != null) {
            model.addAttribute("patient_id", patient.getPhone_number());
            model.addAttribute("bedAssignment", new Bed());
            return "Bed/Bed_Allocate"; // Redirect to the bed assignment page with patient data
        } else {
            model.addAttribute("errorMessage", "Patient not found. Please try again.");
            return "Bed/Bed_Allocate"; // Stay on the same page to try again
        }

    }

    // Handle bed assignment
    @PostMapping("/assign")
    public String assignBed(@ModelAttribute("bedAssignment") Bed bed, Model model) {
        Patient patient = patient_Service_Implementation.getPatientById(bed.getAssinged_patient().getPatient_id());
        if (patient != null && !(bed_Service_implementation.areAllBedsFull())) {
            bed_Service_implementation.assignPatientToBed(patient);

            model.addAttribute("successMessage", "Bed assigned successfully!");
            model.addAttribute("bed", bed);
        } else {
            model.addAttribute("errorMessage",
                    "Failed to assign bed. Bed might be already assigned or patient does not exist.");
        }
        return "Bed/Bed_Allocate"; // Stay on the same page with a success or error message
    }

}
