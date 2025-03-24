package com.osp.inventory_management.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDetailDTO {
    private Long id;
    private Long purchaseOrderId;
    private Long productId;
    private Integer quantity;
    private LocalDateTime receivedDate;
}
