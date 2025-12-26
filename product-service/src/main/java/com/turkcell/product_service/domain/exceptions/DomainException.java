package com.turkcell.product_service.domain.exceptions;

/**
 * Base exception for all domain-related exceptions.
 * All domain exceptions should extend this class.
 */
public abstract class DomainException extends RuntimeException {

    protected DomainException(String message) {
        super(message);
    }

    protected DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
