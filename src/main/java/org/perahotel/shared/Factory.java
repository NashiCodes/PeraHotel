package org.perahotel.shared;

public abstract class Factory<T> {

    public T create(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        return concreteEmployee(name);
    }

    protected abstract T concreteEmployee(String name);
}
