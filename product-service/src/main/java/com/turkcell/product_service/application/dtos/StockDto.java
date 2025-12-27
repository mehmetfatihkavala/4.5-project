package com.turkcell.product_service.application.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Nested DTO for stock information.
 */
public record StockDto(
        @NotNull(message = "Stok miktarı zorunludur") @PositiveOrZero(message = "Stok miktarı negatif olamaz") Integer quantity) {
}
