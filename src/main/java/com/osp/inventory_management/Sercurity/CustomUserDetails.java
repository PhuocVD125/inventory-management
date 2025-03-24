//package com.osp.inventory_management.Sercurity;
//
//import com.osp.inventory_management.entity.Account;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//public class CustomUserDetails implements UserDetails {
//
//    private final Account account;
//
//    public CustomUserDetails(Account account) {
//        this.account = account;
//    }
//
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        // Tạo quyền theo format chuẩn của Spring Security: ROLE_MANAGER / ROLE_EMPLOYEE
////        String roleName = "ROLE_" + account.getRole().getName().name(); // từ enum
////        return List.of(new SimpleGrantedAuthority(roleName));
////    }
//
//    @Override
//    public String getUsername() {
//        return account.getAccountName(); // hoặc email
//    }
//
//    @Override
//    public String getPassword() {
//        return account.getPassword();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() { return true; }
//
//    @Override
//    public boolean isAccountNonLocked() { return true; }
//
//    @Override
//    public boolean isCredentialsNonExpired() { return true; }
//
//    @Override
//    public boolean isEnabled() { return true; }
//}
