package com.osp.inventory_management.payload;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
    private String hashedId; // Mã hóa ID để ẩn đi ID thật

    @NotBlank(message = "Product name must not be blank")
    @Size(min = 2, message = "Product name should have at least 2 characters")
    private String name;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Description must not be blank")
    @Size(min = 2, message = "Description should have at least 2 characters")
    private String description;

    @NotBlank(message = "Location must not be blank")
    @Size(min = 2, message = "Location should have at least 2 characters")
    private String location;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    public ProductDTO(Long id, String name, BigDecimal price, String description, String location, Long categoryId) {
        this.hashedId = encodeId(id);
        this.name = name;
        this.price = price;
        this.description = description;
        this.location = location;
        this.categoryId = categoryId;
    }

    private String encodeId(Long id) {
        return UUID.nameUUIDFromBytes(("product-" + id).getBytes()).toString();
    }
}
