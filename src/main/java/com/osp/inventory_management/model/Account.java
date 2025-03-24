package com.osp.inventory_management.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_name", nullable = false, unique = true)
    private String accountName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    /*
     * One-to-One relationship with User entity.
     * One account can have one user, and one user can have one account.
     *
     *  @OneToOne(mappedBy = "account")	Đánh dấu mối quan hệ 1-1 với class User,
                                        và khóa ngoại nằm bên class User
                                        (vì mappedBy = "account" tức User có thuộc tính account)

     * cascade = CascadeType.ALL	    Khi Account bị thêm/sửa/xóa thì User tương ứng
                                        cũng sẽ tự động được cập nhật theo

     * orphanRemoval = true	        Nếu xóa user khỏi account, thì user đó cũng bị xóa trong DB

     * @JoinColumn(name = "user_id",   Tạo cột user_id trong bảng accounts để ánh xạ tới id trong bảng users.
       referencedColumnName = "id")    Nhưng do mappedBy nên phần này sẽ bị bỏ qua, hoặc có thể là thừa.
     */

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    /*
    * @ManyToMany(fetch = FetchType.EAGER): Khi lấy Account, sẽ load luôn danh sách
    *                                       Role đi kèm (có thể gây nặng nếu bảng lớn)
    *
    * @JoinTable(...): Tạo bảng trung gian account_roles để liên kết nhiều-nhiều
    *
    */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}
