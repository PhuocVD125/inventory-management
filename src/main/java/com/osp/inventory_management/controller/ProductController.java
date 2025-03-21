package com.osp.inventory_management.controller;

import com.osp.inventory_management.payload.ProductDTO;
import com.osp.inventory_management.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // create new product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    // get all products in the system
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    // get products by hashid
    @GetMapping("/details/{hashedId}")
    public ResponseEntity<ProductDTO> getProductDetails(@PathVariable String hashedId) {
        return ResponseEntity.ok(productService.getProductByHashedId(hashedId));
    }

    // update product by hashid
    @PutMapping("/{hashedId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String hashedId, @Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(hashedId, productDTO));
    }
    // delete product by hashid
    @DeleteMapping("/{hashedId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String hashedId) {
        productService.deleteProduct(hashedId);
        return ResponseEntity.noContent().build();
    }
}