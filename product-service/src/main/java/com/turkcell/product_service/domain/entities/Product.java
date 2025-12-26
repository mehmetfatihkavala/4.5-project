package com.turkcell.product_service.domain.entities;

import com.turkcell.product_service.domain.common.AggregateRoot;
import com.turkcell.product_service.domain.valueobjects.*;

import java.math.BigDecimal;

/**
 * Product Aggregate Root.
 * Represents a product in the e-commerce system with all its attributes.
 * This is the main entry point to the Product aggregate.
 */
public class Product extends AggregateRoot<ProductId> {

    private ProductName name;
    private Description description;
    private Money price;
    private Stock stock;

    // Private constructor for controlled instantiation
    private Product() {
        super();
    }

    private Product(ProductId id, ProductName name, Description description, Money price, Stock stock) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // ==================== Factory Methods ====================

    /**
     * Creates a new Product with all required fields.
     *
     * @param name        the product name
     * @param description the product description
     * @param price       the product price
     * @param stock       the stock quantity
     * @return a new Product instance with a generated ID
     */
    public static Product create(ProductName name, Description description, Money price, Stock stock) {
        validateNotNull(name, "Product name");
        validateNotNull(price, "Price");
        validateNotNull(stock, "Stock");

        return new Product(
                ProductId.create(),
                name,
                description != null ? description : Description.empty(),
                price,
                stock);
    }

    /**
     * Creates a new Product with primitive values (convenience factory method).
     *
     * @param name        the product name
     * @param description the product description
     * @param amount      the price amount
     * @param currency    the price currency
     * @param stockQty    the stock quantity
     * @return a new Product instance with a generated ID
     */
    public static Product create(String name, String description, BigDecimal amount, Currency currency, int stockQty) {
        return create(
                ProductName.of(name),
                Description.of(description),
                Money.of(amount, currency),
                Stock.of(stockQty));
    }

    /**
     * Reconstitutes a Product from persistence layer.
     * Used when loading existing products from the database.
     *
     * @param id          the product ID
     * @param name        the product name
     * @param description the product description
     * @param price       the product price
     * @param stock       the stock quantity
     * @return a Product instance with the specified ID
     */
    public static Product reconstitute(ProductId id, ProductName name, Description description, Money price,
            Stock stock) {
        validateNotNull(id, "Product ID");
        validateNotNull(name, "Product name");
        validateNotNull(price, "Price");
        validateNotNull(stock, "Stock");

        return new Product(
                id,
                name,
                description != null ? description : Description.empty(),
                price,
                stock);
    }

    // ==================== Getters ====================

    public ProductName getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    // ==================== Domain Behaviors ====================

    /**
     * Updates the product name.
     *
     * @param newName the new product name
     */
    public void updateName(ProductName newName) {
        validateNotNull(newName, "Product name");
        this.name = newName;
    }

    /**
     * Updates the product description.
     *
     * @param newDescription the new product description
     */
    public void updateDescription(Description newDescription) {
        this.description = newDescription != null ? newDescription : Description.empty();
    }

    /**
     * Updates the product price.
     *
     * @param newPrice the new price
     */
    public void updatePrice(Money newPrice) {
        validateNotNull(newPrice, "Price");
        this.price = newPrice;
    }

    /**
     * Adds stock to the product.
     *
     * @param quantity the quantity to add
     */
    public void addStock(int quantity) {
        this.stock = this.stock.add(quantity);
    }

    /**
     * Removes stock from the product.
     *
     * @param quantity the quantity to remove
     * @throws IllegalArgumentException if insufficient stock
     */
    public void removeStock(int quantity) {
        this.stock = this.stock.subtract(quantity);
    }

    /**
     * Sets the stock quantity directly.
     *
     * @param newStock the new stock
     */
    public void setStock(Stock newStock) {
        validateNotNull(newStock, "Stock");
        this.stock = newStock;
    }

    /**
     * Checks if the product is in stock.
     *
     * @return true if stock quantity is greater than zero
     */
    public boolean isInStock() {
        return stock.isAvailable();
    }

    /**
     * Checks if the product has sufficient stock for the requested quantity.
     *
     * @param requestedQuantity the requested quantity
     * @return true if stock is sufficient
     */
    public boolean hasSufficientStock(int requestedQuantity) {
        return stock.hasAtLeast(requestedQuantity);
    }

    /**
     * Calculates the total price for a given quantity.
     *
     * @param quantity the quantity
     * @return the total price
     */
    public Money calculateTotalPrice(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        return price.multiply(quantity);
    }

    // ==================== Validation Helpers ====================

    private static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    // ==================== Object Overrides ====================

    @Override
    public String toString() {
        return String.format("Product{id=%s, name=%s, price=%s, stock=%s}",
                getId(), name, price, stock);
    }
}
