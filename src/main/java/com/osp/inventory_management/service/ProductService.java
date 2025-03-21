package com.osp.inventory_management.service;

import com.osp.inventory_management.payload.ProductDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(String hashedId, ProductDTO productDTO);
    void deleteProduct(String hashedId);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductByHashedId(String hashedId);
    List<ProductDTO> getProductByName(String name);
}
