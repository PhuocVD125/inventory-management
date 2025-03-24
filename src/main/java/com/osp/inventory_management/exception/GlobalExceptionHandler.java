package com.osp.inventory_management.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied() {
        return "redirect:/error.html?code=403&message=Bạn không có quyền truy cập trang này!";
    }

    @ExceptionHandler(RuntimeException.class)
    public RedirectView handleRuntimeException(RuntimeException ex) {
        return new RedirectView("/error.html?code=500&message=" + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/error.html?code=500&message=Lỗi không xác định!");
    }
}
