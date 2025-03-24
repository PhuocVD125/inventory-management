package com.osp.inventory_management.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
// Đánh dấu lớp này là nơi xử lý ngoại lệ toàn cục (global) cho tất cả các controller.
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    // Khi người dùng không đủ quyền (403), chuyển hướng đến trang lỗi có thông báo rõ ràng.
    public String handleAccessDenied() {
        return "redirect:/error.html?code=403&message=Bạn không có quyền truy cập trang này!";
    }

    @ExceptionHandler(RuntimeException.class)
    // Xử lý tất cả các lỗi Runtime (ví dụ: NullPointerException, IllegalArgumentException, ...)
    // Redirect đến trang lỗi và truyền message lỗi cụ thể để dễ debug.
    public RedirectView handleRuntimeException(RuntimeException ex) {
        return new RedirectView("/error.html?code=500&message=" + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    // Dự phòng cho tất cả các loại lỗi khác không được bắt ở trên.
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/error.html?code=500&message=Lỗi không xác định!");
    }
}
