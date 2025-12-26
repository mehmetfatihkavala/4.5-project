package com.turkcell.product_service.domain.valueobjects;

/**
 * Enum representing supported currencies.
 * ISO 4217 currency codes are used.
 */
public enum Currency {

    TRY("TRY", "Turkish Lira", "₺"),
    USD("USD", "United States Dollar", "$"),
    EUR("EUR", "Euro", "€"),
    GBP("GBP", "British Pound", "£");

    private final String code;
    private final String displayName;
    private final String symbol;

    Currency(String code, String displayName, String symbol) {
        this.code = code;
        this.displayName = displayName;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets a Currency by its ISO 4217 code.
     *
     * @param code the currency code
     * @return the matching Currency
     * @throws IllegalArgumentException if no matching currency is found
     */
    public static Currency fromCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Currency code cannot be null or blank");
        }
        for (Currency currency : values()) {
            if (currency.code.equalsIgnoreCase(code.trim())) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Unknown currency code: " + code);
    }
}
