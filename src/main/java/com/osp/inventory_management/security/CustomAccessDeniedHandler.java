package com.osp.inventory_management.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component // Đánh dấu bean để Spring Boot tự động quét và quản lý
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Hàm xử lý khi người dùng đã xác thực nhưng không có quyền truy cập tài nguyên (HTTP 403)
     *
     * @param request đối tượng HttpServletRequest hiện tại
     * @param response đối tượng HttpServletResponse để trả về cho client
     * @param accessDeniedException ngoại lệ được ném ra khi truy cập bị từ chối
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Thiết lập mã phản hồi HTTP là 403 - Forbidden
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // Thiết lập kiểu nội dung trả về là JSON (áp dụng cho API)
        response.setContentType("application/json");

        // Ghi thông báo lỗi trả về cho client
        response.getWriter().write("{\"error\": \"Bạn không có quyền truy cập vào tài nguyên này!\"}");

        // (Tuỳ chọn mở rộng): Ghi log hoặc redirect nếu là ứng dụng trả về giao diện HTML
        // response.sendRedirect("/error/403"); // nếu là web MVC
    }
}
