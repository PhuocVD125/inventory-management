package com.osp.inventory_management.mapper;


import com.osp.inventory_management.model.Product;
import com.osp.inventory_management.model.PurchaseOrder;
import com.osp.inventory_management.model.PurchaseOrderDetail;
import com.osp.inventory_management.payload.PurchaseOrderDetailDTO;

public class PurchaseOrderDetailMapper {

    public static PurchaseOrderDetailDTO toDTO(PurchaseOrderDetail entity) {
        Long productId = entity.getProduct() != null ? entity.getProduct().getId() : null;
        Long purchaseOrderId = entity.getPurchaseOrder() != null ? entity.getPurchaseOrder().getId() : null;

        return new PurchaseOrderDetailDTO(
                entity.getId(),
                purchaseOrderId,
                productId,
                entity.getQuantity()
//                entity.getReceivedDate()
        );
    }

    public static PurchaseOrderDetail toEntity(PurchaseOrderDetailDTO dto, PurchaseOrder order, Product product) {
        PurchaseOrderDetail detail = new PurchaseOrderDetail();
        detail.setId(dto.getId());
        detail.setPurchaseOrder(order);
        detail.setProduct(product);
        detail.setQuantity(dto.getQuantity());
//        detail.setReceivedDate(dto.getReceivedDate());
        return detail;
    }
}
