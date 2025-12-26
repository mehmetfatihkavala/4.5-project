package com.turkcell.product_service.domain.valueobjects;

import com.turkcell.product_service.domain.common.ValueObject;

/**
 * Value object representing the name of a Product.
 * Ensures the name is not null or blank and has a reasonable length.
 */
public final class ProductName extends ValueObject {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 255;

    private final String value;

    private ProductName(String value) {
        validate(value);
        this.value = value.trim();
    }

    /**
     * Creates a ProductName from a string value.
     *
     * @param value the product name
     * @return a new ProductName instance
     * @throws IllegalArgumentException if the value is invalid
     */
    public static ProductName of(String value) {
        return new ProductName(value);
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or blank");
        }
        String trimmed = value.trim();
        if (trimmed.length() < MIN_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("Product name must be at least %d characters long", MIN_LENGTH));
        }
        if (trimmed.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("Product name cannot exceed %d characters", MAX_LENGTH));
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[] { value };
    }

    @Override
    public String toString() {
        return value;
    }
}
