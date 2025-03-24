package com.osp.inventory_management.payload;

import lombok.Data;

@Data
public class AccountRegisterRequest {
    private String accountName;
    private String password;
    private String email;
    private String role; // "EMPLOYEE" hoặc "MANAGER"

    // Thêm các trường thông tin User
    private String fullName;
    private String phone;
    private String address;
}
