package com.turkcell.product_service.application.services;

import com.turkcell.product_service.application.dtos.*;
import com.turkcell.product_service.application.ports.ProductServicePort;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.exceptions.ProductNotFoundException;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.domain.valueobjects.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application Service implementation for Product operations.
 * Implements all product use cases.
 */
@Service
public class ProductService implements ProductServicePort {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        // Convert DTO to domain value objects
        ProductName name = ProductName.of(request.name());
        Description description = Description.of(request.description());
        Money price = Money.of(request.price().amount(), Currency.fromCode(request.price().currency()));
        Stock stock = Stock.of(request.stock().quantity());

        // Create domain entity
        Product product = Product.create(name, description, price, stock);

        // Save and return response
        Product savedProduct = productRepository.save(product);
        return toProductResponse(savedProduct);
    }

    @Override
    public ProductResponse getProductById(String id) {
        ProductId productId = ProductId.of(id);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return toProductResponse(product);
    }

    @Override
    public ProductListResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responses = products.stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
        return new ProductListResponse(responses, responses.size());
    }

    @Override
    public ProductResponse updateProduct(String id, UpdateProductRequest request) {
        ProductId productId = ProductId.of(id);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        // Update only provided fields
        if (request.name() != null && !request.name().isBlank()) {
            product.updateName(ProductName.of(request.name()));
        }
        if (request.description() != null && !request.description().isBlank()) {
            product.updateDescription(Description.of(request.description()));
        }
        if (request.price() != null) {
            Money newPrice = Money.of(request.price().amount(), Currency.fromCode(request.price().currency()));
            product.updatePrice(newPrice);
        }
        if (request.stock() != null) {
            product.setStock(Stock.of(request.stock().quantity()));
        }

        // Save and return response
        Product updatedProduct = productRepository.save(product);
        return toProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        ProductId productId = ProductId.of(id);
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }
        productRepository.deleteById(productId);
    }

    /**
     * Converts a domain Product to a ProductResponse DTO.
     */
    private ProductResponse toProductResponse(Product product) {
        PriceDto priceDto = new PriceDto(
                product.getPrice().getAmount(),
                product.getPrice().getCurrency().getCode());
        StockDto stockDto = new StockDto(product.getStock().getQuantity());

        return new ProductResponse(
                product.getId().toString(),
                product.getName().getValue(),
                product.getDescription().getValue(),
                priceDto,
                stockDto);
    }
}
