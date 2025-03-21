package com.osp.inventory_management.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    @GetMapping
    public String listProducts() {
        return "list"; // trả về templates/list.html
    }

    @GetMapping("/create")
    public String showCreatePage() {
        return "create"; // trả về templates/create.html
    }

    @GetMapping("/edit")
    public String showEditPage() {
        return "edit"; // trả về templates/edit.html (lấy hashedId từ URL query hoặc localStorage)
    }
}
