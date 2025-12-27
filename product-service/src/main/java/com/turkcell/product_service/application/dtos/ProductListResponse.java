package com.turkcell.product_service.application.dtos;

import java.util.List;

/**
 * Response DTO for a list of products.
 */
public record ProductListResponse(
        List<ProductResponse> products,
        int totalCount) {
}
