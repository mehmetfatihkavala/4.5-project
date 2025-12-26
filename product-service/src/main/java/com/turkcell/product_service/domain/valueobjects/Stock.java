package com.turkcell.product_service.domain.valueobjects;

import com.turkcell.product_service.domain.common.ValueObject;

/**
 * Value object representing the stock quantity of a Product.
 * Ensures stock is never negative and provides operations for stock management.
 */
public final class Stock extends ValueObject {

    private final int quantity;

    private Stock(int quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    /**
     * Creates a Stock with the specified quantity.
     *
     * @param quantity the stock quantity
     * @return a new Stock instance
     * @throws IllegalArgumentException if quantity is negative
     */
    public static Stock of(int quantity) {
        return new Stock(quantity);
    }

    /**
     * Creates a Stock with zero quantity.
     *
     * @return a new Stock instance with zero quantity
     */
    public static Stock zero() {
        return new Stock(0);
    }

    private void validate(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Checks if the stock is empty (zero quantity).
     *
     * @return true if quantity is zero
     */
    public boolean isEmpty() {
        return quantity == 0;
    }

    /**
     * Checks if the stock is available (quantity greater than zero).
     *
     * @return true if quantity is greater than zero
     */
    public boolean isAvailable() {
        return quantity > 0;
    }

    /**
     * Checks if the stock has at least the specified quantity.
     *
     * @param requiredQuantity the required quantity
     * @return true if stock quantity is greater than or equal to required quantity
     */
    public boolean hasAtLeast(int requiredQuantity) {
        return quantity >= requiredQuantity;
    }

    /**
     * Adds quantity to the stock.
     *
     * @param amount the amount to add
     * @return a new Stock with increased quantity
     * @throws IllegalArgumentException if amount is negative
     */
    public Stock add(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to add cannot be negative");
        }
        return new Stock(this.quantity + amount);
    }

    /**
     * Subtracts quantity from the stock.
     *
     * @param amount the amount to subtract
     * @return a new Stock with decreased quantity
     * @throws IllegalArgumentException if amount is negative or would result in
     *                                  negative stock
     */
    public Stock subtract(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to subtract cannot be negative");
        }
        if (amount > this.quantity) {
            throw new IllegalArgumentException(
                    String.format("Insufficient stock: available=%d, requested=%d", this.quantity, amount));
        }
        return new Stock(this.quantity - amount);
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[] { quantity };
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }
}
