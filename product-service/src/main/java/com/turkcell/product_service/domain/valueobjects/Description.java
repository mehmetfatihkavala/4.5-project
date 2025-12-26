package com.turkcell.product_service.domain.valueobjects;

import com.turkcell.product_service.domain.common.ValueObject;

/**
 * Value object representing the description of a Product.
 * Description can be optional but if provided, it must not exceed the maximum
 * length.
 */
public final class Description extends ValueObject {

    private static final int MAX_LENGTH = 2000;

    private final String value;

    private Description(String value) {
        validate(value);
        this.value = value != null ? value.trim() : null;
    }

    /**
     * Creates a Description from a string value.
     *
     * @param value the description text (can be null or empty)
     * @return a new Description instance
     * @throws IllegalArgumentException if the value exceeds maximum length
     */
    public static Description of(String value) {
        return new Description(value);
    }

    /**
     * Creates an empty Description.
     *
     * @return a new Description instance with null value
     */
    public static Description empty() {
        return new Description(null);
    }

    private void validate(String value) {
        if (value != null && value.trim().length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("Description cannot exceed %d characters", MAX_LENGTH));
        }
    }

    public String getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == null || value.isBlank();
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[] { value };
    }

    @Override
    public String toString() {
        return value != null ? value : "";
    }
}
