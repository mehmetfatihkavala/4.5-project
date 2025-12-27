package com.turkcell.product_service.application.dtos;

import jakarta.validation.constraints.Size;

/**
 * DTO for updating an existing product.
 * All fields are optional - only provided fields will be updated.
 */
public record UpdateProductRequest(
        @Size(min = 2, message = "Ürün adı en az 2 karakter olmalıdır") String name,

        @Size(min = 10, message = "Açıklama en az 10 karakter olmalıdır") String description,

        PriceDto price,

        StockDto stock) {
}
