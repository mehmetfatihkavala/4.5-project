package com.turkcell.product_service.domain.exceptions;

/**
 * Exception thrown when there is insufficient stock for an operation.
 */
public class InsufficientStockException extends DomainException {

    public InsufficientStockException(int available, int requested) {
        super(String.format("Insufficient stock: available=%d, requested=%d", available, requested));
    }

    public InsufficientStockException(String message) {
        super(message);
    }
}
