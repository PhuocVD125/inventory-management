package com.osp.inventory_management.service.impl;

import com.osp.inventory_management.mapper.InventoryMapper;
import com.osp.inventory_management.model.Inventory;
import com.osp.inventory_management.payload.InventoryDTO;
import com.osp.inventory_management.repository.InventoryRepository;
import com.osp.inventory_management.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public List<InventoryDTO> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toUnmodifiableList());
    }
}
