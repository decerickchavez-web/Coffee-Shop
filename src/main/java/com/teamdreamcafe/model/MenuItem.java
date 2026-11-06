package com.teamdreamcafe.model;

import java.util.Objects;

/**
 * Base class for everything the cafe sells.
 *
 * <p>This class is the abstraction at the centre of the model: the user
 * interface talks to {@code MenuItem} objects and asks {@link #priceFor(Size)},
 * so new product types (for example food) can be added later without changing
 * the ordering screens. Pricing is abstract, which is what makes the design
 * polymorphic.</p>
 */
public abstract class MenuItem {

    private final String id;
    private final String name;
    private final Category category;

    protected MenuItem(String id, String name, Category category) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Menu item id is required.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Menu item name is required.");
        }
        Objects.requireNonNull(category, "category");
        this.id = id.trim();
        this.name = name.trim();
        this.category = category;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    /**
     * Price of one serving in the given size, in Philippine pesos.
     */
    public abstract double priceFor(Size size);

    /**
     * Human-readable product type, used by reports and receipts.
     */
    public abstract String kind();

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MenuItem menuItem)) {
            return false;
        }
        return id.equals(menuItem.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
