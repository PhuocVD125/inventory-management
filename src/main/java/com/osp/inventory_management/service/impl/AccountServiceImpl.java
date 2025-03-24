package com.osp.inventory_management.service.impl;

import com.osp.inventory_management.model.Account;
import com.osp.inventory_management.model.User;
import com.osp.inventory_management.model.Role;
import com.osp.inventory_management.payload.AccountRegisterRequest;
import com.osp.inventory_management.repository.AccountRepository;
import com.osp.inventory_management.repository.RoleRepository;
import com.osp.inventory_management.repository.UserRepository;
import com.osp.inventory_management.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Account registerNewAccount(AccountRegisterRequest request) {
        // check accountName đã tồn tại
        if (accountRepository.existsByAccountName(request.getAccountName())) {
            throw new RuntimeException("Tài khoản đã tồn tại!");
        }

        // Gán role mặc định
        Role role = roleRepository.findByName(Role.RoleName.EMPLOYEE)
                .orElseThrow(() -> new RuntimeException("Vai trò mặc định không tồn tại!"));
        // Tạo account mới
        Account account = new Account();
        account.setAccountName(request.getAccountName());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setEmail(request.getEmail());
        account.setRoles(Collections.singleton(role));

        // Tạo user mới
        User user = new User();
        user.setName(request.getFullName());
        user.setPhoneNumber(request.getPhone());
        user.setAddress(request.getAddress());
        user.setAccount(account); // gắn account cho user
        account.setUser(user);    // gắn user cho account

        return accountRepository.save(account);
    }
}
