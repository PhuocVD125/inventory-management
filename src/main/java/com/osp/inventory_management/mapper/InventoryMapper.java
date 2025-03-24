package com.osp.inventory_management.mapper;

import com.osp.inventory_management.model.Inventory;
import com.osp.inventory_management.payload.InventoryDTO;

public class InventoryMapper {
    public static InventoryDTO toDTO(Inventory inventory) {
        InventoryDTO dto = new InventoryDTO();
        dto.setId(inventory.getId());
        dto.setProductId(inventory.getProduct().getId());
        dto.setQuantity(inventory.getQuantity());
        dto.setLastUpdated(inventory.getLastUpdated());
        return dto;
    }

    // Optional: Nếu bạn cần ánh xạ ngược (không bắt buộc dùng)
    // public static Inventory toEntity(InventoryDTO dto, Product product) { ... }
}
