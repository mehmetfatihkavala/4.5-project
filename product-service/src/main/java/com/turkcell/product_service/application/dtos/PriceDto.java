package com.turkcell.product_service.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Nested DTO for price information.
 */
public record PriceDto(
        @NotNull(message = "Fiyat tutarı zorunludur") @Positive(message = "Fiyat pozitif olmalıdır") BigDecimal amount,

        @NotBlank(message = "Para birimi zorunludur") String currency) {
}
