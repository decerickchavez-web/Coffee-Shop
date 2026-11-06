package com.teamdreamcafe.model;

/**
 * A drink on the menu. Every drink has a fixed medium and large price, taken
 * from the shop's price board.
 */
public class Drink extends MenuItem {

    private final double mediumPrice;
    private final double largePrice;

    public Drink(String id, String name, Category category, double mediumPrice, double largePrice) {
        super(id, name, category);
        if (mediumPrice <= 0 || largePrice <= 0) {
            throw new IllegalArgumentException("Drink prices must be greater than zero.");
        }
        if (largePrice < mediumPrice) {
            throw new IllegalArgumentException("Large price cannot be lower than the medium price.");
        }
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
    }

    public double mediumPrice() {
        return mediumPrice;
    }

    public double largePrice() {
        return largePrice;
    }

    @Override
    public double priceFor(Size size) {
        return size == Size.LARGE ? largePrice : mediumPrice;
    }

    @Override
    public String kind() {
        return "Drink";
    }
}
