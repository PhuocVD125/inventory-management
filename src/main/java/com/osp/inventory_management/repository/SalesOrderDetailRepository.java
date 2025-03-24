package com.osp.inventory_management.repository;

import com.osp.inventory_management.model.SalesOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderDetailRepository extends JpaRepository<SalesOrderDetail, Long> {
}
