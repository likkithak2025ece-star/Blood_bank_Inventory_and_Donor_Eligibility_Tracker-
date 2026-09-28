package com.blood.bank.service;

import com.blood.bank.entity.Donor;
import com.blood.bank.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    // Add donor
    public Donor addDonor(Donor donor) {

        donor.setEligible(checkEligibility(donor));

        return donorRepository.save(donor);
    }

    // Get all donors
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    // Get donor by ID
    public Donor getDonorById(Long id) {
        return donorRepository.findById(id).orElse(null);
    }

    // Get only eligible donors
    public List<Donor> getEligibleDonors() {
        return donorRepository.findByEligibleTrue();
    }

    // Search donors by blood group
public List<Donor> getDonorsByBloodGroup(String bloodGroup) {
    return donorRepository.findByBloodGroup(bloodGroup);
}
// Search donors by name
public List<Donor> getDonorsByName(String name) {
    return donorRepository.findByNameContainingIgnoreCase(name);
}

// Search donors by age
public List<Donor> getDonorsByAge(int age) {
    return donorRepository.findByAge(age);
}

    // Update donor
    public Donor updateDonor(Long id, Donor donor) {

        Donor existingDonor = donorRepository.findById(id).orElse(null);

        if (existingDonor != null) {

            existingDonor.setName(donor.getName());
            existingDonor.setAge(donor.getAge());
            existingDonor.setGender(donor.getGender());
            existingDonor.setBloodGroup(donor.getBloodGroup());
            existingDonor.setPhone(donor.getPhone());
            existingDonor.setEmail(donor.getEmail());
            existingDonor.setLastDonationDate(donor.getLastDonationDate());

            // Automatically calculate eligibility again
            existingDonor.setEligible(checkEligibility(existingDonor));

            return donorRepository.save(existingDonor);
        }

        return null;
    }

    // Delete donor
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    // Eligibility calculation
    private boolean checkEligibility(Donor donor) {

        // Age must be between 18 and 60
        if (donor.getAge() < 18 || donor.getAge() > 60) {
            return false;
        }

        // If donor has never donated before
        if (donor.getLastDonationDate() == null) {
            return true;
        }

        // Calculate days since last donation
        long daysSinceDonation = ChronoUnit.DAYS.between(
                donor.getLastDonationDate(),
                LocalDate.now()
        );

        // Minimum 90 days between donations
        return daysSinceDonation >= 90;
    }
}