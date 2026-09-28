package com.blood.bank.controller;

import com.blood.bank.entity.BloodInventory;
import com.blood.bank.service.BloodInventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class BloodInventoryController {

    private final BloodInventoryService bloodInventoryService;

    public BloodInventoryController(BloodInventoryService bloodInventoryService) {
        this.bloodInventoryService = bloodInventoryService;
    }

    // Add blood inventory
    @PostMapping
    public BloodInventory addInventory(@RequestBody BloodInventory inventory) {
        return bloodInventoryService.addInventory(inventory);
    }

    // Get all blood inventory
    @GetMapping
    public List<BloodInventory> getAllInventory() {
        return bloodInventoryService.getAllInventory();
    }

    // Get inventory by ID
    @GetMapping("/id/{id}")
    public BloodInventory getInventoryById(@PathVariable Long id) {
        return bloodInventoryService.getInventoryById(id);
    }

    // Search inventory by blood group
    @GetMapping("/blood/{bloodGroup}")
    public List<BloodInventory> getInventoryByBloodGroup(
            @PathVariable String bloodGroup) {

        return bloodInventoryService.getInventoryByBloodGroup(bloodGroup);
    }

    // Update inventory
    @PutMapping("/{id}")
    public BloodInventory updateInventory(
            @PathVariable Long id,
            @RequestBody BloodInventory inventory) {

        return bloodInventoryService.updateInventory(id, inventory);
    }

    // Delete inventory
    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Long id) {

        bloodInventoryService.deleteInventory(id);

        return "Blood inventory deleted successfully";
    }
}