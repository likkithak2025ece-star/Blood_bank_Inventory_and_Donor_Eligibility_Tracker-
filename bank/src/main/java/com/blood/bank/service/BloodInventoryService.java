package com.blood.bank.service;

import com.blood.bank.entity.BloodInventory;
import com.blood.bank.repository.BloodInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodInventoryService {

    private final BloodInventoryRepository bloodInventoryRepository;

    public BloodInventoryService(BloodInventoryRepository bloodInventoryRepository) {
        this.bloodInventoryRepository = bloodInventoryRepository;
    }

    // Add blood inventory
    public BloodInventory addInventory(BloodInventory inventory) {
        return bloodInventoryRepository.save(inventory);
    }

    // Get all blood inventory
    public List<BloodInventory> getAllInventory() {
        return bloodInventoryRepository.findAll();
    }

    // Get inventory by ID
    public BloodInventory getInventoryById(Long id) {
        return bloodInventoryRepository.findById(id).orElse(null);
    }

    // Search inventory by blood group
    public List<BloodInventory> getInventoryByBloodGroup(String bloodGroup) {
        return bloodInventoryRepository.findByBloodGroup(bloodGroup);
    }

    // Update inventory
    public BloodInventory updateInventory(Long id, BloodInventory inventory) {

        BloodInventory existingInventory =
                bloodInventoryRepository.findById(id).orElse(null);

        if (existingInventory != null) {

            existingInventory.setBloodGroup(inventory.getBloodGroup());
            existingInventory.setQuantity(inventory.getQuantity());
            existingInventory.setExpiryDate(inventory.getExpiryDate());
            existingInventory.setStorageLocation(inventory.getStorageLocation());

            return bloodInventoryRepository.save(existingInventory);
        }

        return null;
    }

    // Delete inventory
    public void deleteInventory(Long id) {
        bloodInventoryRepository.deleteById(id);
    }
}