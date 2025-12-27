package com.turkcell.product_service.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * DTO for creating a new product.
 */
public record CreateProductRequest(
        @NotBlank(message = "Ürün adı boş olamaz") @Size(min = 2, message = "Ürün adı en az 2 karakter olmalıdır") String name,

        @NotBlank(message = "Açıklama boş olamaz") @Size(min = 10, message = "Açıklama en az 10 karakter olmalıdır") String description,

        @NotNull(message = "Fiyat bilgisi zorunludur") PriceDto price,

        @NotNull(message = "Stok bilgisi zorunludur") StockDto stock) {
}
