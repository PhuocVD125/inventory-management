package com.osp.inventory_management.payload;

import com.osp.inventory_management.model.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class InventoryDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private LocalDateTime lastUpdated;
}
