package com.osp.inventory_management.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeViewController {
    @GetMapping
    public String homepage() {
        return "home";
    }
}
