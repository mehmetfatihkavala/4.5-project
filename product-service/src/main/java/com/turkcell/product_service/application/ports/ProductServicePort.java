package com.turkcell.product_service.application.ports;

import com.turkcell.product_service.application.dtos.*;

/**
 * Application Service Port for Product operations.
 * Defines the use cases available for the Web layer.
 */
public interface ProductServicePort {

    /**
     * Creates a new product.
     *
     * @param request the product creation request
     * @return the created product response
     */
    ProductResponse createProduct(CreateProductRequest request);

    /**
     * Retrieves a product by its ID.
     *
     * @param id the product ID
     * @return the product response
     */
    ProductResponse getProductById(String id);

    /**
     * Retrieves all products.
     *
     * @return the list of all products
     */
    ProductListResponse getAllProducts();

    /**
     * Updates an existing product.
     *
     * @param id      the product ID
     * @param request the update request
     * @return the updated product response
     */
    ProductResponse updateProduct(String id, UpdateProductRequest request);

    /**
     * Deletes a product by its ID.
     *
     * @param id the product ID
     */
    void deleteProduct(String id);
}
