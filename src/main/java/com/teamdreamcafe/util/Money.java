package com.teamdreamcafe.util;

import java.util.Locale;

/**
 * Money helpers. All amounts are stored as {@code double} pesos and rounded to
 * two decimals at every calculation step so totals match the receipt.
 *
 * <p>If the peso sign does not render on a particular computer, change
 * {@link #SYMBOL} to {@code "PHP "} in this one place.</p>
 */
public final class Money {

    public static final String SYMBOL = "\u20B1";

    private Money() {
        // Utility class.
    }

    /** Rounds to two decimals (half-up for positive amounts). */
    public static double round2(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }

    /** Example: {@code ₱48.00}. */
    public static String format(double amount) {
        return SYMBOL + String.format(Locale.US, "%,.2f", round2(amount));
    }

    /** Example: {@code ₱48} for whole amounts, otherwise {@code ₱48.50}. */
    public static String compact(double amount) {
        double rounded = round2(amount);
        if (Math.abs(rounded - Math.rint(rounded)) < 0.0001) {
            return SYMBOL + String.format(Locale.US, "%,.0f", rounded);
        }
        return SYMBOL + String.format(Locale.US, "%,.2f", rounded);
    }
}
