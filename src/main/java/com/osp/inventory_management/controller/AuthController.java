package com.osp.inventory_management.controller;

import com.osp.inventory_management.payload.AccountRegisterRequest;
import com.osp.inventory_management.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AccountRegisterRequest request) {
        accountService.registerNewAccount(request);
        return ResponseEntity.ok("Tạo tài khoản thành công!");
    }
}
