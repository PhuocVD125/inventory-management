package com.osp.inventory_management.service.impl;
import com.osp.inventory_management.entity.Category;
import com.osp.inventory_management.entity.Product;
import com.osp.inventory_management.payload.ProductDTO;
import com.osp.inventory_management.repository.CategoryRepository;
import com.osp.inventory_management.repository.ProductRepository;
import com.osp.inventory_management.service.ProductService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private ModelMapper mapper;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ModelMapper mapper, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // convert DTO to entity
        Product product = mapToEntity(productDTO);
        product.setCategory(category);
        // save and return DTO
        return mapToDto(productRepository.save(product));
    }

    private ProductDTO mapToDto(Product save) {
        ProductDTO productDTO = mapper.map(save, ProductDTO.class);
        return productDTO;
    }

    private Product mapToEntity(ProductDTO productDTO) {
        Product product = mapper.map(productDTO, Product.class);
        return product;
    }


    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // convert DTO to entity
        Product updatedProduct = mapToEntity(productDTO);
        updatedProduct.setId(id);

        // save and return DTO
        return mapToDto(productRepository.save(updatedProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToDto(product);
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        List<Product> products = productRepository.findByCategory(categoryId);
        return products.stream().map((product) -> mapToDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> mapToDto(product)).collect(Collectors.toList());
    }
}
