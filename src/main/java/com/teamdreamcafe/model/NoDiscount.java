package com.teamdreamcafe.model;

/**
 * Default policy: the customer pays the full amount.
 */
public enum NoDiscount implements DiscountPolicy {

    INSTANCE;

    @Override
    public String label() {
        return "None";
    }

    @Override
    public double discountFor(double subtotal) {
        return 0.0;
    }
}
