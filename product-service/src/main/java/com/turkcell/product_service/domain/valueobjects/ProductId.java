package com.turkcell.product_service.domain.valueobjects;

import com.turkcell.product_service.domain.common.ValueObject;

import java.util.UUID;

/**
 * Value object representing the unique identifier of a Product.
 * Uses UUID for globally unique identification.
 */
public final class ProductId extends ValueObject {

    private final UUID value;

    private ProductId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("ProductId value cannot be null");
        }
        this.value = value;
    }

    /**
     * Creates a new ProductId with a random UUID.
     *
     * @return a new ProductId instance
     */
    public static ProductId create() {
        return new ProductId(UUID.randomUUID());
    }

    /**
     * Creates a ProductId from an existing UUID.
     *
     * @param value the UUID value
     * @return a new ProductId instance
     */
    public static ProductId of(UUID value) {
        return new ProductId(value);
    }

    /**
     * Creates a ProductId from a string representation of a UUID.
     *
     * @param value the string representation of the UUID
     * @return a new ProductId instance
     * @throws IllegalArgumentException if the string is not a valid UUID
     */
    public static ProductId of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ProductId string value cannot be null or blank");
        }
        return new ProductId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[] { value };
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
