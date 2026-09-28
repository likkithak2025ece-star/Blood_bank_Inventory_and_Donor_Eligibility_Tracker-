package com.blood.bank.service;

import com.blood.bank.entity.BloodRequest;
import com.blood.bank.repository.BloodRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodRequestService {

    private final BloodRequestRepository repository;

    public BloodRequestService(BloodRequestRepository repository) {
        this.repository = repository;
    }

    public BloodRequest addRequest(BloodRequest request) {

        if (request.getStatus() == null ||
            request.getStatus().isBlank()) {

            request.setStatus("Pending");
        }

        if (request.getRequestDate() == null) {
            request.setRequestDate(
                java.time.LocalDate.now()
            );
        }

        return repository.save(request);
    }

    public List<BloodRequest> getAllRequests() {
        return repository.findAll();
    }

    public BloodRequest getRequestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<BloodRequest> getRequestsByStatus(String status) {
        return repository.findByStatusIgnoreCase(status);
    }

    public List<BloodRequest> getRequestsByBloodGroup(
            String bloodGroup) {

        return repository.findByBloodGroup(bloodGroup);
    }

    public BloodRequest updateRequest(
            Long id,
            BloodRequest request) {

        BloodRequest existing =
                repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setPatientName(request.getPatientName());
        existing.setBloodGroup(request.getBloodGroup());
        existing.setUnitsRequired(request.getUnitsRequired());
        existing.setHospital(request.getHospital());
        existing.setRequestDate(request.getRequestDate());
        existing.setStatus(request.getStatus());

        return repository.save(existing);
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }

    public long getPendingCount() {
        return repository.countByStatusIgnoreCase("Pending");
    }
}