package com.osp.inventory_management.controller.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase-orders")
public class PurchaseViewController {
    @GetMapping
    public String listPurchaseOrder() {
        return "purchase_module/list_purchase_order";
    }
    @GetMapping("/create")
    public String createPurchaseOrder() {
        return "purchase_module/create_purchase_order";
    }
    @GetMapping("/approve")
    public String approvePurchaseOrder() {
        return "purchase_module/approve_purchase_order";
    }
}
