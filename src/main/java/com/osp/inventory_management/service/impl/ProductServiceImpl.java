package com.osp.inventory_management.service.impl;

import com.osp.inventory_management.entity.Category;
import com.osp.inventory_management.entity.Product;
import com.osp.inventory_management.payload.ProductDTO;
import com.osp.inventory_management.repository.CategoryRepository;
import com.osp.inventory_management.repository.ProductRepository;
import com.osp.inventory_management.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ModelMapper mapper, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setId(null); // đảm bảo là persist chứ không merge
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setLocation(productDTO.getLocation());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(
                encodeId(savedProduct.getId()), // hashedId
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getDescription(),
                savedProduct.getLocation(),
                savedProduct.getCategory().getId()
        );
    }


    @Override
    @Transactional
    public ProductDTO updateProduct(String hashedId, ProductDTO productDTO) {
        Long realId = decodeId(hashedId); // Chỉ gọi trong service
        Product existingProduct = productRepository.findById(realId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setLocation(productDTO.getLocation());


        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        Product updatedProduct = productRepository.saveAndFlush(existingProduct);
        return new ProductDTO(encodeId(updatedProduct.getId()), updatedProduct.getName(),
                updatedProduct.getPrice(), updatedProduct.getDescription(),
                updatedProduct.getLocation(), updatedProduct.getCategory().getId());
    }

    @Override
    @Transactional
    public void deleteProduct(String hashedId) {
        Long realId = decodeId(hashedId); // Chỉ gọi trong service
        Product product = productRepository.findById(realId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(
                        encodeId(product.getId()), // Mã hóa ID
                        product.getName(),
                        product.getPrice(),
                        product.getDescription(),
                        product.getLocation(),
                        product.getCategory().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO getProductByHashedId(String hashedId) {
        Long realId = decodeId(hashedId);
        Product product = productRepository.findById(realId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductDTO(
                encodeId(product.getId()), // Trả về hashedId
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getLocation(),
                product.getCategory().getId()
        );
    }

    private Long decodeId(String hashedId) {
        // Đoạn này có thể được thay thế bằng mapping stored hash-ID nếu muốn tăng bảo mật
        return productRepository.findAll()
                .stream()
                .filter(p -> encodeId(p.getId()).equals(hashedId))
                .map(Product::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid Hashed ID"));
    }

    private String encodeId(Long id) {
        return UUID.nameUUIDFromBytes(("product-" + id).getBytes()).toString();
    }
}
