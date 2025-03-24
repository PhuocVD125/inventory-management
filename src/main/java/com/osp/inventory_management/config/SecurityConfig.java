package com.osp.inventory_management.config;

import com.osp.inventory_management.config.CustomUserDetailsService;
import com.osp.inventory_management.security.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration // Đánh dấu đây là 1 class cấu hình Spring
@EnableWebSecurity // Bật cấu hình bảo mật Spring Security
@RequiredArgsConstructor // Tự động tạo constructor cho các trường final
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler; // Xử lý khi người dùng truy cập không đủ quyền

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Sử dụng thuật toán mã hóa BCrypt cho mật khẩu
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Cho phép sử dụng AuthenticationManager từ Spring để xử lý xác thực đăng nhập
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt bảo vệ CSRF vì bạn dùng REST hoặc đơn giản hóa dev frontend

                .authorizeHttpRequests(auth -> auth
                        // Cho phép truy cập không cần đăng nhập vào trang login và thư mục static
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()

                        // Chỉ ROLE_MANAGER được phép truy cập trang phê duyệt đơn hàng
                        .requestMatchers("/purchase-orders/approve/**").hasRole("MANAGER")

                        // Cả EMPLOYEE và MANAGER đều có quyền truy cập các trang này
                        .requestMatchers("/purchase-orders/**", "/products/**", "/inventory/**")
                        .hasAnyRole("EMPLOYEE", "MANAGER")

                        // Các API khác yêu cầu xác thực (authenticated)
                        .requestMatchers("/api/**").authenticated()

                        // Các route khác không cần bảo vệ
                        .anyRequest().permitAll()
                )

                // Nếu truy cập bị từ chối, gọi CustomAccessDeniedHandler
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler)
                )

                // Hoặc chuyển hướng tới trang lỗi 403 tùy biến
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/error/403")
                )

                // Cấu hình form login mặc định
                .formLogin(form -> form
                        .loginPage("/login")                  // Đường dẫn trang login (GET)
                        .loginProcessingUrl("/login")         // Endpoint xử lý form login (POST)
                        .defaultSuccessUrl("/home", true)     // Sau login thành công thì chuyển tới /home
                        .failureUrl("/login?error=true")      // Nếu login fail thì quay lại login và báo lỗi
                        .permitAll()
                );

        return http.build();
    }
}
