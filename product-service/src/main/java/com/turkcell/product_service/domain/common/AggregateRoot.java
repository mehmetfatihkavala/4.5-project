package com.turkcell.product_service.domain.common;

/**
 * Base class for all aggregate roots in the domain.
 * An aggregate root is the main entry point to an aggregate cluster of domain
 * objects.
 *
 * @param <TId> The type of the aggregate root's identifier
 */
public abstract class AggregateRoot<TId> extends Entity<TId> {

    protected AggregateRoot() {
        super();
    }

    protected AggregateRoot(TId id) {
        super(id);
    }
}
