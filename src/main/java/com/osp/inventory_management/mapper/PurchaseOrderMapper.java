package com.osp.inventory_management.mapper;


import com.osp.inventory_management.model.PurchaseOrder;
import com.osp.inventory_management.model.Supplier;
import com.osp.inventory_management.model.User;
import com.osp.inventory_management.payload.PurchaseOrderDTO;
import com.osp.inventory_management.payload.PurchaseOrderDetailDTO;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PurchaseOrderMapper {

    // Entity → DTO
    public static PurchaseOrderDTO toDTO(PurchaseOrder entity) {
        PurchaseOrderDTO dto = new PurchaseOrderDTO();
        dto.setId(entity.getId());

        dto.setSupplierId(entity.getSupplier() != null ? entity.getSupplier().getId() : null);
        dto.setCreatedById(entity.getCreatedBy() != null ? entity.getCreatedBy().getId() : null);
        dto.setApprovedById(entity.getApprovedBy() != null ? entity.getApprovedBy().getId() : null);

        dto.setOrderDate(entity.getOrderDate());

        dto.setStatus(entity.getStatus());

        // Map danh sách chi tiết đơn hàng
        List<PurchaseOrderDetailDTO> detailDTOs = entity.getDetails() != null
                ? entity.getDetails().stream()
                .filter(Objects::nonNull)
                .map(PurchaseOrderDetailMapper::toDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();

        dto.setDetails(detailDTOs);

        return dto;
    }

    // DTO → Entity
    public static PurchaseOrder toEntity(PurchaseOrderDTO dto, Supplier supplier, User createdBy, User approvedBy) {
        PurchaseOrder order = new PurchaseOrder();
        order.setId(dto.getId());
        order.setSupplier(supplier);
        order.setCreatedBy(createdBy);
        order.setApprovedBy(approvedBy);
        order.setOrderDate(dto.getOrderDate());
        order.setStatus(dto.getStatus());

        // Chi tiết đơn hàng sẽ được xử lý bên ngoài service (sau khi có product entity)
        return order;
    }
}
