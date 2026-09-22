package com.omnistock.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.omnistock.inventory.entity.Inventory;
import com.omnistock.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existingInventory =
                inventoryRepository.findById(id).orElse(null);

        if (existingInventory != null) {
            existingInventory.setProductId(inventory.getProductId());
            existingInventory.setWarehouse(inventory.getWarehouse());
            existingInventory.setQuantity(inventory.getQuantity());
            existingInventory.setReorderLevel(inventory.getReorderLevel());

            return inventoryRepository.save(existingInventory);
        }

        return null;
    }

    public Inventory reduceQuantity(Long productId, Integer quantity) {

        Inventory inventory =
                inventoryRepository.findByProductId(productId);

        if (inventory != null && inventory.getQuantity() >= quantity) {

            int newQuantity =
                    inventory.getQuantity() - quantity;

            inventory.setQuantity(newQuantity);

            Inventory updatedInventory =
                    inventoryRepository.save(inventory);

            // Low-stock alert
            if (newQuantity <= inventory.getReorderLevel()) {
                System.out.println(
                        "LOW STOCK ALERT: Product "
                        + productId
                        + " in warehouse "
                        + inventory.getWarehouse()
                        + " has only "
                        + newQuantity
                        + " items remaining."
                );
            }

            return updatedInventory;
        }

        return null;
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}