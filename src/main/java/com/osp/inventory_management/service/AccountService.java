package com.osp.inventory_management.service;

import com.osp.inventory_management.model.Account;
import com.osp.inventory_management.payload.AccountRegisterRequest;

public interface AccountService {
    public Account registerNewAccount(AccountRegisterRequest request);
}
