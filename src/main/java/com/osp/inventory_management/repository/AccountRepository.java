package com.osp.inventory_management.repository;

import com.osp.inventory_management.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Object> findByAccountName(String username);
}
