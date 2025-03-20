package com.osp.inventory_management.repository;

import com.osp.inventory_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Long CategoryId);
}
