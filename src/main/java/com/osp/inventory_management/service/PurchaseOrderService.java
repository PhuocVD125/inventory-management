package com.osp.inventory_management.service;

import com.osp.inventory_management.model.PurchaseOrder;
import com.osp.inventory_management.payload.PurchaseOrderDTO;

import java.util.List;

public interface PurchaseOrderService {
    // create a new order with list order details  , supplier,..
    PurchaseOrder createOrder(PurchaseOrderDTO orderDTO);
    // manager approve
    void approveOrder(Long orderId, Long approverId);
    PurchaseOrderDTO getPurchaseOrderById(Long orderId);
    List<PurchaseOrderDTO> getAllPurchaseOrders();
}
