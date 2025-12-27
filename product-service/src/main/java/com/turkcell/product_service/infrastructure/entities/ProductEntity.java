package com.turkcell.product_service.infrastructure.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;

/**
 * JPA Entity representing a Product in the persistence layer.
 * This is a data-oriented representation used for persistence,
 * separate from the domain entity.
 */
// Note: JPA annotations are commented out since we're using in-memory storage
// for now
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal priceAmount;

    @Column(nullable = false, length = 3)
    private String priceCurrency;

    @Column(nullable = false)
    private int stockQuantity;

    // Default constructor for JPA
    public ProductEntity() {
    }

    public ProductEntity(UUID id, String name, String description,
            BigDecimal priceAmount, String priceCurrency, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
