package com.osp.inventory_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    private String location;

    // Nhiều sản phẩm thuộc 1 danh mục
    // Khóa ngoại trỏ đến bảng Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    // Một sản phẩm có thể nằm trong nhiều chi tiết của các đơn hàng mua
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchaseOrderDetail> purchaseOrderDetails = new HashSet<>();

    // Một sản phẩm có thể nằm trong nhiều chi tiết của các đơn hàng bán
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SalesOrderDetail> saleOrderDetails = new HashSet<>();
}
