package com.turkcell.product_service.infrastructure.repositories;

import com.turkcell.product_service.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA Repository for ProductEntity.
 */
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {

    /**
     * Finds products by name containing the given string (case-insensitive).
     */
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
