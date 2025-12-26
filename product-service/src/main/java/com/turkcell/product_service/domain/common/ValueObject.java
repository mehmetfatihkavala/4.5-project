package com.turkcell.product_service.domain.common;

/**
 * Base class for all value objects in the domain.
 * Value objects are immutable objects that describe some characteristic or
 * attribute
 * but carry no identity. They are compared by their attribute values.
 */
public abstract class ValueObject {

    /**
     * Returns the attributes of this value object for equality comparison.
     * Subclasses must implement this to define which attributes are used for
     * equality.
     *
     * @return an array of objects representing the attributes of this value object
     */
    protected abstract Object[] getEqualityComponents();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ValueObject that = (ValueObject) o;
        Object[] thisComponents = getEqualityComponents();
        Object[] thatComponents = that.getEqualityComponents();

        if (thisComponents.length != thatComponents.length)
            return false;

        for (int i = 0; i < thisComponents.length; i++) {
            if (thisComponents[i] == null) {
                if (thatComponents[i] != null)
                    return false;
            } else if (!thisComponents[i].equals(thatComponents[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (Object component : getEqualityComponents()) {
            result = 31 * result + (component == null ? 0 : component.hashCode());
        }
        return result;
    }
}
