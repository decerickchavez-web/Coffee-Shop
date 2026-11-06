package com.teamdreamcafe.model;

/**
 * Strategy for discounts. Using an interface here keeps the discount rules out
 * of {@link Order}: new promos can be added as new classes without editing the
 * order logic.
 */
public interface DiscountPolicy {

    /** Short label shown on receipts and in the order summary. */
    String label();

    /** Amount to subtract from the given subtotal. */
    double discountFor(double subtotal);
}
