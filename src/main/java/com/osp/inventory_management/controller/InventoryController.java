package com.osp.inventory_management.controller;

import com.osp.inventory_management.payload.InventoryDTO;
import com.osp.inventory_management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // get all inventories
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }
}
