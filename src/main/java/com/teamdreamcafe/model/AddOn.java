package com.teamdreamcafe.model;

import java.util.Objects;

/**
 * A paid extra that can be attached to a drink (pearls, nata, whipped cream...).
 */
public class AddOn {

    private final String name;
    private final double price;

    public AddOn(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Add-on name is required.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Add-on price must be greater than zero.");
        }
        this.name = name.trim();
        this.price = price;
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddOn addOn)) {
            return false;
        }
        return Double.compare(addOn.price, price) == 0 && name.equals(addOn.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return name;
    }
}
