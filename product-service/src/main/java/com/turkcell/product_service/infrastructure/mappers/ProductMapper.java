package com.turkcell.product_service.infrastructure.mappers;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.*;
import com.turkcell.product_service.infrastructure.entities.ProductEntity;

/**
 * Mapper class for converting between Domain entities and Infrastructure
 * entities.
 * Provides static methods for bidirectional conversion.
 */
public final class ProductMapper {

    private ProductMapper() {
        // Utility class, prevent instantiation
    }

    /**
     * Converts a Domain Product to an Infrastructure ProductEntity.
     *
     * @param product the domain Product
     * @return the corresponding ProductEntity
     */
    public static ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductEntity(
                product.getId().getValue(),
                product.getName().getValue(),
                product.getDescription().getValue(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency().getCode(),
                product.getStock().getQuantity());
    }

    /**
     * Converts an Infrastructure ProductEntity to a Domain Product.
     *
     * @param entity the ProductEntity
     * @return the corresponding domain Product
     */
    public static Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        return Product.reconstitute(
                ProductId.of(entity.getId()),
                ProductName.of(entity.getName()),
                Description.of(entity.getDescription()),
                Money.of(entity.getPriceAmount(), Currency.fromCode(entity.getPriceCurrency())),
                Stock.of(entity.getStockQuantity()));
    }
}
