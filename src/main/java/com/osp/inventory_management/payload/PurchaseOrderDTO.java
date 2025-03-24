package com.osp.inventory_management.payload;

import com.osp.inventory_management.model.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {
    private Long id;
    private Long supplierId;
    private Long createdById;
    private Long approvedById; // Có thể null nếu chưa phê duyệt
//    private LocalDateTime orderDate;
    private PurchaseOrder.OrderStatus status;
    private List<PurchaseOrderDetailDTO> details;
}