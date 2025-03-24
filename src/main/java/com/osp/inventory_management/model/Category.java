package com.osp.inventory_management.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    //    One Category have n Product
    //    mappedBy = "category"	Quan hệ này được ánh xạ bởi trường category trong class Product.
    //                          Tức Product là chủ sở hữu (owning side) của quan hệ.
    //    cascade = CascadeType.ALL Khi thực hiện các thao tác persist, remove, merge... trên Category,
    //                              các Product liên quan cũng bị ảnh hưởng tương ứng.
    //    orphanRemoval = true	Nếu xóa Product khỏi products list của Category,
    //                      thì Product sẽ bị xóa luôn khỏi DB (nếu không còn tham chiếu nào khác).

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
