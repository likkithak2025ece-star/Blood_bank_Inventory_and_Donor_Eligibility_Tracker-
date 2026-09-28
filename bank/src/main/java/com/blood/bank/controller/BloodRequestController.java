package com.blood.bank.controller;

import com.blood.bank.entity.BloodRequest;
import com.blood.bank.service.BloodRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class BloodRequestController {

    private final BloodRequestService service;

    public BloodRequestController(
            BloodRequestService service) {

        this.service = service;
    }

    @PostMapping
    public BloodRequest addRequest(
            @RequestBody BloodRequest request) {

        return service.addRequest(request);
    }

    @GetMapping
    public List<BloodRequest> getAllRequests() {

        return service.getAllRequests();
    }

    @GetMapping("/{id:\\d+}")
    public BloodRequest getRequestById(
            @PathVariable Long id) {

        return service.getRequestById(id);
    }

    @GetMapping("/status/{status}")
    public List<BloodRequest> getRequestsByStatus(
            @PathVariable String status) {

        return service.getRequestsByStatus(status);
    }

    @GetMapping("/blood/{bloodGroup}")
    public List<BloodRequest> getRequestsByBloodGroup(
            @PathVariable String bloodGroup) {

        return service.getRequestsByBloodGroup(bloodGroup);
    }

    @PutMapping("/{id}")
    public BloodRequest updateRequest(
            @PathVariable Long id,
            @RequestBody BloodRequest request) {

        return service.updateRequest(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(
            @PathVariable Long id) {

        service.deleteRequest(id);

        return "Blood request deleted successfully";
    }

    @GetMapping("/pending/count")
    public long getPendingCount() {

        return service.getPendingCount();
    }
}