package com.blood.bank.controller;

import com.blood.bank.entity.Donor;
import com.blood.bank.service.DonorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    // Add donor
    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorService.addDonor(donor);
    }

    // Get all donors
    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    // Get donor by ID
   @GetMapping("/{id:\\d+}")
    public Donor getDonorById(@PathVariable Long id) {
    return donorService.getDonorById(id);
}

    // Get only eligible donors
    @GetMapping("/eligible")
    public List<Donor> getEligibleDonors() {
        return donorService.getEligibleDonors();
    }

    // Search donors by blood group
@GetMapping("/blood/{bloodGroup}")
public List<Donor> getDonorsByBloodGroup(
        @PathVariable String bloodGroup) {

    return donorService.getDonorsByBloodGroup(bloodGroup);
}

// Search donors by name
@GetMapping("/name/{name}")
public List<Donor> getDonorsByName(
        @PathVariable String name) {

    return donorService.getDonorsByName(name);
}

// Search donors by age
@GetMapping("/age/{age}")
public List<Donor> getDonorsByAge(
        @PathVariable int age) {

    return donorService.getDonorsByAge(age);
}

    // Update donor
    @PutMapping("/{id}")
    public Donor updateDonor(
            @PathVariable Long id,
            @RequestBody Donor donor) {

        return donorService.updateDonor(id, donor);
    }

    // Delete donor
    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);

        return "Donor deleted successfully";
    }
}
