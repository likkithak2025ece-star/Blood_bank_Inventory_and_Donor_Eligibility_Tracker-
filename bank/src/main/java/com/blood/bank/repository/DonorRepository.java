package com.blood.bank.repository;

import com.blood.bank.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findByEligibleTrue();

    List<Donor> findByBloodGroup(String bloodGroup);
    List<Donor> findByNameContainingIgnoreCase(String name);
    List<Donor> findByAge(int age);
}