package com.turkcell.product_service.domain.repositories;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.ProductId;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Product aggregate.
 * Defines the contract for product persistence operations.
 * Implementation will be provided in the Infrastructure layer.
 */
public interface ProductRepository {

    /**
     * Saves a product.
     *
     * @param product the product to save
     * @return the saved product
     */
    Product save(Product product);

    /**
     * Finds a product by its ID.
     *
     * @param id the product ID
     * @return an Optional containing the product if found
     */
    Optional<Product> findById(ProductId id);

    /**
     * Finds all products.
     *
     * @return a list of all products
     */
    List<Product> findAll();

    /**
     * Deletes a product by its ID.
     *
     * @param id the product ID to delete
     */
    void deleteById(ProductId id);

    /**
     * Checks if a product exists by its ID.
     *
     * @param id the product ID
     * @return true if the product exists
     */
    boolean existsById(ProductId id);

    /**
     * Finds products by name containing the given string (case-insensitive).
     *
     * @param name the name to search for
     * @return a list of matching products
     */
    List<Product> findByNameContaining(String name);
}
