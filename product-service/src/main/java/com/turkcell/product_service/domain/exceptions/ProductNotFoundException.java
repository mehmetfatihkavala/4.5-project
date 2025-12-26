package com.turkcell.product_service.domain.exceptions;

import com.turkcell.product_service.domain.valueobjects.ProductId;

/**
 * Exception thrown when a product is not found.
 */
public class ProductNotFoundException extends DomainException {

    public ProductNotFoundException(ProductId id) {
        super(String.format("Product not found with ID: %s", id));
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
