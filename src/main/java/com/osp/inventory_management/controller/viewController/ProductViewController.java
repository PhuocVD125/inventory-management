package com.osp.inventory_management.controller.viewController;

import com.osp.inventory_management.payload.ProductDTO;
import com.osp.inventory_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductViewController {


    // Danh sách tất cả sản phẩm
    @GetMapping
    public String listProducts() {
        return "list";
    }
}
