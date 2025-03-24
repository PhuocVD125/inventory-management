package com.osp.inventory_management.repository;

import com.osp.inventory_management.model.Inventory;
import com.osp.inventory_management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct(Product product);
}
