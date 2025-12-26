package com.turkcell.product_service.domain.common;

import java.util.Objects;

/**
 * Base class for all entities in the domain.
 * Entities are objects that have a distinct identity that runs through time and
 * different representations.
 *
 * @param <TId> The type of the entity's identifier
 */
public abstract class Entity<TId> {

    protected TId id;

    protected Entity() {
    }

    protected Entity(TId id) {
        this.id = id;
    }

    public TId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
