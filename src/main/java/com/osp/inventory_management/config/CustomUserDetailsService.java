package com.osp.inventory_management.config;

import com.osp.inventory_management.model.Account;
import com.osp.inventory_management.repository.AccountRepository;
import com.osp.inventory_management.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.osp.inventory_management.security.CustomUserDetails;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccountName(accountName)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy account: " + accountName));

        System.out.println("Đang load account: " + accountName);

        return new CustomUserDetails(account);
    }
}
