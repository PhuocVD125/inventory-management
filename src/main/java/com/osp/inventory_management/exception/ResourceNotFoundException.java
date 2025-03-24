package com.osp.inventory_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
// Annotation này dùng để tự động trả về mã HTTP 404 (Not Found)
// khi exception này được ném ra trong controller.
public class ResourceNotFoundException extends RuntimeException {

    // Tên của tài nguyên bị lỗi (ví dụ: "Product", "User")
    private String resourceName;

    // Tên của trường được dùng để tìm kiếm tài nguyên (ví dụ: "id", "email")
    private String fieldName;

    // Giá trị của trường được dùng để tìm kiếm (ví dụ: 5)
    private long fieldValue;

    /**
     * Constructor: Nhận tên tài nguyên, trường tìm kiếm và giá trị không tìm thấy.
     * Ví dụ: new ResourceNotFoundException("Product", "id", 5)
     * -> Thông báo lỗi: "Product not found with id : '5'"
     */
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    // Getter để lấy tên tài nguyên
    public String getResourceName() {
        return resourceName;
    }

    // Getter để lấy tên trường tìm kiếm
    public String getFieldName() {
        return fieldName;
    }

    // Getter để lấy giá trị trường tìm kiếm
    public long getFieldValue() {
        return fieldValue;
    }
}
