package com.osp.inventory_management.service;

import com.osp.inventory_management.payload.InventoryDTO;
import com.osp.inventory_management.payload.ProductDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventory();
}
