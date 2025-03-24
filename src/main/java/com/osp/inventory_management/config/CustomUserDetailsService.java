package com.osp.inventory_management.config;

import com.osp.inventory_management.model.Account;
import com.osp.inventory_management.repository.AccountRepository;
import com.osp.inventory_management.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Đánh dấu class này là một Spring Service để Spring có thể quản lý như một Bean.
@RequiredArgsConstructor // Tự động sinh constructor với các trường final (ở đây là accountRepository)
public class CustomUserDetailsService implements UserDetailsService {
    // Giao tiếp với DB để tìm kiếm Account theo accountName (tên đăng nhập)
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        // Truy vấn account từ DB theo accountName.
        // Nếu không tìm thấy, ném ngoại lệ để Spring Security xử lý (ví dụ: báo sai tài khoản)
        Account account = accountRepository.findByAccountName(accountName)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy account: " + accountName));

        // In ra log (console) để kiểm tra quá trình login
        System.out.println("Đang load account: " + accountName);

        // Trả về một object CustomUserDetails chứa account – object này sẽ được Spring Security sử dụng để xác thực.
        return new CustomUserDetails(account);
    }
}
