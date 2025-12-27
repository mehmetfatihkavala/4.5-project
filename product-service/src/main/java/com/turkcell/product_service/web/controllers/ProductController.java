package com.turkcell.product_service.web.controllers;

import com.turkcell.product_service.application.dtos.*;
import com.turkcell.product_service.application.ports.ProductServicePort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Product operations.
 * Handles all HTTP requests for product CRUD operations.
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductServicePort productService;

    public ProductController(ProductServicePort productService) {
        this.productService = productService;
    }

    /**
     * GET /api/v1/products - Retrieves all products.
     */
    @GetMapping
    public ResponseEntity<ProductListResponse> getAllProducts() {
        ProductListResponse response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/v1/products/{id} - Retrieves a product by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/v1/products - Creates a new product.
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * PUT /api/v1/products/{id} - Updates an existing product.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody UpdateProductRequest request) {
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE /api/v1/products/{id} - Deletes a product.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
