package com.turkcell.product_service.domain.valueobjects;

import com.turkcell.product_service.domain.common.ValueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Value object representing monetary value with amount and currency.
 * Encapsulates both Price and Currency as a single cohesive concept.
 * Uses BigDecimal for precise monetary calculations.
 */
public final class Money extends ValueObject {

    private final BigDecimal amount;
    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        validate(amount, currency);
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    /**
     * Creates a Money instance with the specified amount and currency.
     *
     * @param amount   the monetary amount
     * @param currency the currency
     * @return a new Money instance
     * @throws IllegalArgumentException if amount is null or negative, or currency
     *                                  is null
     */
    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    /**
     * Creates a Money instance from a double value and currency.
     *
     * @param amount   the monetary amount as double
     * @param currency the currency
     * @return a new Money instance
     */
    public static Money of(double amount, Currency currency) {
        return new Money(BigDecimal.valueOf(amount), currency);
    }

    /**
     * Creates a Money instance with zero amount in the specified currency.
     *
     * @param currency the currency
     * @return a new Money instance with zero amount
     */
    public static Money zero(Currency currency) {
        return new Money(BigDecimal.ZERO, currency);
    }

    private void validate(BigDecimal amount, Currency currency) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    /**
     * Adds another Money to this Money.
     * Both must have the same currency.
     *
     * @param other the Money to add
     * @return a new Money representing the sum
     * @throws IllegalArgumentException if currencies don't match
     */
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add money with different currencies");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * Subtracts another Money from this Money.
     * Both must have the same currency.
     *
     * @param other the Money to subtract
     * @return a new Money representing the difference
     * @throws IllegalArgumentException if currencies don't match or result would be
     *                                  negative
     */
    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot subtract money with different currencies");
        }
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Result cannot be negative");
        }
        return new Money(result, this.currency);
    }

    /**
     * Multiplies this Money by a factor.
     *
     * @param factor the multiplication factor
     * @return a new Money representing the product
     */
    public Money multiply(int factor) {
        if (factor < 0) {
            throw new IllegalArgumentException("Factor cannot be negative");
        }
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)), this.currency);
    }

    /**
     * Checks if this Money is greater than another Money.
     *
     * @param other the Money to compare
     * @return true if this Money is greater
     * @throws IllegalArgumentException if currencies don't match
     */
    public boolean isGreaterThan(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot compare money with different currencies");
        }
        return this.amount.compareTo(other.amount) > 0;
    }

    /**
     * Checks if this Money is less than another Money.
     *
     * @param other the Money to compare
     * @return true if this Money is less
     * @throws IllegalArgumentException if currencies don't match
     */
    public boolean isLessThan(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot compare money with different currencies");
        }
        return this.amount.compareTo(other.amount) < 0;
    }

    /**
     * Checks if this Money represents zero amount.
     *
     * @return true if amount is zero
     */
    public boolean isZero() {
        return amount.compareTo(BigDecimal.ZERO) == 0;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[] { amount, currency };
    }

    @Override
    public String toString() {
        return currency.getSymbol() + amount.toPlainString();
    }
}
