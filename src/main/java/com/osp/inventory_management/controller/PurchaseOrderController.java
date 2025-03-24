package com.osp.inventory_management.controller;

import com.osp.inventory_management.model.PurchaseOrder;
import com.osp.inventory_management.payload.PurchaseOrderDTO;
import com.osp.inventory_management.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    // 1. Tạo đơn hàng
    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createOrder(@RequestBody PurchaseOrderDTO orderDTO) {
        PurchaseOrder savedOrder = purchaseOrderService.createOrder(orderDTO);
        PurchaseOrderDTO responseDTO = purchaseOrderService.getPurchaseOrderById(savedOrder.getId());
        return ResponseEntity.ok(responseDTO);
    }

    // 2. Phê duyệt đơn hàng
    @PutMapping("/{orderId}/approve/{approverId}")
    public ResponseEntity<String> approveOrder(
            @PathVariable Long orderId,
            @PathVariable Long approverId
    ) {
        purchaseOrderService.approveOrder(orderId, approverId);
        return ResponseEntity.ok("Đơn hàng #" + orderId + " đã được phê duyệt bởi người dùng #" + approverId);
    }

    // 3. Lấy đơn hàng theo ID
    @GetMapping("/{orderId}")
    public ResponseEntity<PurchaseOrderDTO> getOrderById(@PathVariable Long orderId) {
        PurchaseOrderDTO dto = purchaseOrderService.getPurchaseOrderById(orderId);
        return ResponseEntity.ok(dto);
    }

    // 4. Lấy tất cả đơn hàng
    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> getAllOrders() {
        List<PurchaseOrderDTO> list = purchaseOrderService.getAllPurchaseOrders();
        return ResponseEntity.ok(list);
    }
}