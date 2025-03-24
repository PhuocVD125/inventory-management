package com.osp.inventory_management.service.impl;

import com.osp.inventory_management.exception.ResourceNotFoundException;
import com.osp.inventory_management.mapper.PurchaseOrderDetailMapper;
import com.osp.inventory_management.mapper.PurchaseOrderMapper;
import com.osp.inventory_management.model.*;
import com.osp.inventory_management.payload.PurchaseOrderDTO;
import com.osp.inventory_management.repository.*;
import com.osp.inventory_management.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderDetailRepository purchaseOrderDetailRepository, SupplierRepository supplierRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderDetailRepository = purchaseOrderDetailRepository;
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public PurchaseOrder createOrder(PurchaseOrderDTO orderDTO) {
        Supplier supplier = supplierRepository.findById(orderDTO.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found ", "id", orderDTO.getSupplierId()));

        User creator = userRepository.findById(orderDTO.getCreatedById())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found ","id", orderDTO.getCreatedById()));

        PurchaseOrder order = PurchaseOrderMapper.toEntity(orderDTO, supplier, creator, null);
        order.setStatus(PurchaseOrder.OrderStatus.PENDING);

        // Lưu đơn hàng (để có ID)
        PurchaseOrder savedOrder = purchaseOrderRepository.save(order);

        // Lưu chi tiết đơn hàng
        List<PurchaseOrderDetail> details = orderDTO.getDetails().stream().map(detailDTO -> {
            Product product = productRepository.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: ", "id", detailDTO.getProductId()));
            return PurchaseOrderDetailMapper.toEntity(detailDTO, savedOrder, product);
        }).collect(Collectors.toList());

        purchaseOrderDetailRepository.saveAll(details);
        savedOrder.setDetails(details);

        return savedOrder;
    }

    @Override
    public void approveOrder(Long orderId, Long approverId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found", "id", orderId));

        if (order.getStatus() != PurchaseOrder.OrderStatus.PENDING) {
            throw new ResourceNotFoundException("Only pending orders can be approved", "id", orderId);
        }

        User approver = userRepository.findById(approverId)
                .orElseThrow(() -> new ResourceNotFoundException("Approver not found", "id", orderId));

        order.setApprovedBy(approver);
        order.setStatus(PurchaseOrder.OrderStatus.COMPLETED);

        purchaseOrderRepository.save(order);

    }

    @Override
    public PurchaseOrderDTO getPurchaseOrderById(Long orderId) {
        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Purchase order not found"));
        return PurchaseOrderMapper.toDTO(order);
    }

    @Override
    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
        return orders.stream().map(PurchaseOrderMapper::toDTO).collect(Collectors.toList());
    }
}
