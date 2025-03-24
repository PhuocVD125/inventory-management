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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cho phép sử dụng AuthenticationManager để xử lý login
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Cấu hình bảo mật
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // tắt CSRF nếu dùng REST API
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/manager/**").hasRole("MANAGER")       // Chỉ MANAGER
//                        .requestMatchers("/api/employee/**").hasAnyRole("EMPLOYEE", "MANAGER") // Cả 2 đều truy cập được
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/purchase-orders/approve/**").hasRole("MANAGER")
                        .requestMatchers("/purchase-orders/**", "/products/**", "/inventory/**").hasAnyRole("EMPLOYEE", "MANAGER")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()

                )

                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler)
                )

                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/error/403")  // Đường dẫn tới trang lỗi
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                );

        return http.build();
    }
}
