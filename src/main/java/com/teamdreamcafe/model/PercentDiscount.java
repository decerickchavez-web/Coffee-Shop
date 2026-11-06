package com.teamdreamcafe.model;

import com.teamdreamcafe.util.Money;

/**
 * Percentage discount, used for the Philippine Senior Citizen / PWD 20% rule
 * and for simple promo codes.
 */
public class PercentDiscount implements DiscountPolicy {

    private final double rate;
    private final String label;

    public PercentDiscount(double rate, String label) {
        if (rate <= 0 || rate >= 1) {
            throw new IllegalArgumentException("Discount rate must be between 0 and 1 (exclusive).");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("Discount label is required.");
        }
        this.rate = rate;
        this.label = label.trim();
    }

    /** Standard 20% Senior Citizen / PWD discount. */
    public static PercentDiscount seniorOrPwd() {
        return new PercentDiscount(0.20, "Senior / PWD (20%)");
    }

    @Override
    public String label() {
        return label;
    }

    @Override
    public double discountFor(double subtotal) {
        if (subtotal <= 0) {
            return 0.0;
        }
        return Money.round2(subtotal * rate);
    }

    public double rate() {
        return rate;
    }
}
