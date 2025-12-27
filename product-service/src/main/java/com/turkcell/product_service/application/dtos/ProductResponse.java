package com.turkcell.product_service.application.dtos;

import java.math.BigDecimal;

/**
 * Response DTO for a single product.
 */
public record ProductResponse(
        String id,
        String name,
        String description,
        PriceDto price,
        StockDto stock) {
}
