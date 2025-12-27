package com.turkcell.product_service.infrastructure.repositories;

import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import com.turkcell.product_service.domain.valueobjects.ProductId;
import com.turkcell.product_service.infrastructure.entities.ProductEntity;
import com.turkcell.product_service.infrastructure.mappers.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory implementation of ProductRepository.
 * Uses a ConcurrentHashMap for thread-safe storage.
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<UUID, ProductEntity> storage = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        storage.put(entity.getId(), entity);
        return ProductMapper.toDomain(entity);
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        ProductEntity entity = storage.get(id.getValue());
        return Optional.ofNullable(ProductMapper.toDomain(entity));
    }

    @Override
    public List<Product> findAll() {
        return storage.values().stream()
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(ProductId id) {
        storage.remove(id.getValue());
    }

    @Override
    public boolean existsById(ProductId id) {
        return storage.containsKey(id.getValue());
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        String lowerCaseName = name.toLowerCase();
        return storage.values().stream()
                .filter(entity -> entity.getName().toLowerCase().contains(lowerCaseName))
                .map(ProductMapper::toDomain)
                .collect(Collectors.toList());
    }
}
