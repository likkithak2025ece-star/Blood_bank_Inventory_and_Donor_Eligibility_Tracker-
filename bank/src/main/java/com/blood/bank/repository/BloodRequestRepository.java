package com.blood.bank.repository;

import com.blood.bank.entity.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepository
        extends JpaRepository<BloodRequest, Long> {

    List<BloodRequest> findByStatusIgnoreCase(String status);

    List<BloodRequest> findByBloodGroup(String bloodGroup);

    long countByStatusIgnoreCase(String status);
}