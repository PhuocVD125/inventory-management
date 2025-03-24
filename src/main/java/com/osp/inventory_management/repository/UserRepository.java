package com.osp.inventory_management.repository;

import com.osp.inventory_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
