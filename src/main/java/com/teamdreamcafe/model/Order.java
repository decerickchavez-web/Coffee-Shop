package com.teamdreamcafe.model;

import com.teamdreamcafe.util.Money;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A customer order: the list of lines, the discount policy that applies and the
 * money totals.
 *
 * <p>The class protects its own state (encapsulation): lines can only be added
 * or removed through methods that validate the input, and callers receive an
 * unmodifiable view of the line list.</p>
 */
public class Order {

    private final int number;
    private final LocalDateTime createdAt;
    private final List<OrderItem> items = new ArrayList<>();
    private DiscountPolicy discountPolicy = NoDiscount.INSTANCE;

    public Order(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Order number must be positive.");
        }
        this.number = number;
        this.createdAt = LocalDateTime.now();
    }

    public int number() {
        return number;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    /** Receipt reference such as {@code TDC-1001}. */
    public String reference() {
        return "TDC-" + number;
    }

    /**
     * Adds a line. If the same drink, size and add-ons are already in the
     * order, the quantities are merged instead of creating a duplicate row.
     */
    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Order line is required.");
        }
        for (OrderItem existing : items) {
            if (existing.matches(item.drink(), item.size(), item.addOns())) {
                existing.addQuantity(item.quantity());
                return;
            }
        }
        items.add(item);
    }

    /** Removes the line at the given index. */
    public void removeItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("No order line at position " + index + ".");
        }
        items.remove(index);
    }

    /** Empties the order and resets the discount. */
    public void clear() {
        items.clear();
        discountPolicy = NoDiscount.INSTANCE;
    }

    /** Read-only view of the order lines. */
    public List<OrderItem> items() {
        return Collections.unmodifiableList(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    /** Number of cups in the order (quantities included). */
    public int totalQuantity() {
        int total = 0;
        for (OrderItem item : items) {
            total += item.quantity();
        }
        return total;
    }

    public DiscountPolicy discountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(DiscountPolicy policy) {
        this.discountPolicy = policy == null ? NoDiscount.INSTANCE : policy;
    }

    public double subtotal() {
        double total = 0.0;
        for (OrderItem item : items) {
            total += item.lineTotal();
        }
        return Money.round2(total);
    }

    public double discountAmount() {
        return Money.round2(discountPolicy.discountFor(subtotal()));
    }

    /** Amount the customer has to pay. */
    public double total() {
        return Money.round2(subtotal() - discountAmount());
    }

    @Override
    public String toString() {
        return reference() + " (" + totalQuantity() + " item(s), " + Money.format(total()) + ")";
    }
}
