package com.teamdreamcafe.model;

/**
 * The menu sections of Team Dream Cafe.
 *
 * <p>Each constant carries the label shown in the user interface, so the UI
 * never has to hard-code section names.</p>
 */
public enum Category {

    MATCHA_SERIES("Matcha Series"),
    COFFEE_BASED("Coffee Based"),
    SPECIAL_COFFEE("Special Coffee Based"),
    MILK_TEA("Milk Tea Baby"),
    MILK_BASED("Milk Based"),
    SPECIAL_MILK_BASED("Special Milk Based"),
    FRUIT_TEA("Fruit Tea Series"),
    FRAPPE_COFFEE("Frappe Coffee Based"),
    FRAPPE_MILK("Frappe Milk Based");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
