package com.teamdreamcafe.model;

import com.teamdreamcafe.util.Money;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * One line of an order: a drink, a size, any add-ons and a quantity.
 *
 * <p>Line totals are calculated here, not in the user interface, so the same
 * rules are reused by the receipt, the reports and the tests.</p>
 */
public class OrderItem {

    private final Drink drink;
    private final Size size;
    private final List<AddOn> addOns;
    private int quantity;

    public OrderItem(Drink drink, Size size, List<AddOn> addOns, int quantity) {
        if (drink == null) {
            throw new IllegalArgumentException("Please choose a drink.");
        }
        if (size == null) {
            throw new IllegalArgumentException("Please choose a size.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1.");
        }
        this.drink = drink;
        this.size = size;
        this.addOns = addOns == null ? List.of() : List.copyOf(addOns);
        this.quantity = quantity;
    }

    public Drink drink() {
        return drink;
    }

    public Size size() {
        return size;
    }

    public List<AddOn> addOns() {
        return addOns;
    }

    public int quantity() {
        return quantity;
    }

    /** Increases the quantity (used when the same drink is added twice). */
    public void addQuantity(int extra) {
        if (extra < 1) {
            throw new IllegalArgumentException("Extra quantity must be at least 1.");
        }
        quantity += extra;
    }

    public double addOnsTotal() {
        double total = 0.0;
        for (AddOn addOn : addOns) {
            total += addOn.price();
        }
        return Money.round2(total);
    }

    /** Drink price for the chosen size plus all add-ons, for one cup. */
    public double unitPrice() {
        return Money.round2(drink.priceFor(size) + addOnsTotal());
    }

    /** Unit price multiplied by the quantity. */
    public double lineTotal() {
        return Money.round2(unitPrice() * quantity);
    }

    /** Comma-separated add-on names, or {@code "-"} when there are none. */
    public String addOnSummary() {
        if (addOns.isEmpty()) {
            return "-";
        }
        List<String> names = new ArrayList<>();
        for (AddOn addOn : addOns) {
            names.add(addOn.name());
        }
        return String.join(", ", names);
    }

    /**
     * True when another requested drink has the same drink, size and add-ons,
     * so the two lines can be merged into one row.
     */
    public boolean matches(Drink otherDrink, Size otherSize, List<AddOn> otherAddOns) {
        List<AddOn> compared = otherAddOns == null ? List.of() : otherAddOns;
        return drink.equals(otherDrink)
                && size == otherSize
                && new HashSet<>(addOns).equals(new HashSet<>(compared));
    }

    @Override
    public String toString() {
        return quantity + " x " + drink.name() + " (" + size.label() + ")";
    }
}
