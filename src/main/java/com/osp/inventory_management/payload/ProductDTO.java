package com.osp.inventory_management.payload;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String hashedId; // Mã hóa ID để ẩn đi ID thật
    private String name;
    private BigDecimal price;
    private String description;
    private String location;
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
