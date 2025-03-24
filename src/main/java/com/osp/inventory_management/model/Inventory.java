package com.osp.inventory_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    // Thiết lập quan hệ 1-1 với entity Product
    // @JoinColumn xác định khóa ngoại (FK) product_id trỏ tới bảng product
    // nullable = false đảm bảo không có inventory nào mà không gắn product
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;
}