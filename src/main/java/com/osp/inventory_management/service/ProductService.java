package com.osp.inventory_management.service;

import com.osp.inventory_management.payload.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProductsByCategory(Long categoryId);
    List<ProductDTO> getAllProducts();
}
