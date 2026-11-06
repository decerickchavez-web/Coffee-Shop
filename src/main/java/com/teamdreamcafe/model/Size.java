package com.teamdreamcafe.model;

/**
 * The two cup sizes offered by the shop (M and L on the menu boards).
 */
public enum Size {

    MEDIUM("Medium", "M"),
    LARGE("Large", "L");

    private final String label;
    private final String code;

    Size(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return label;
    }

    public String code() {
        return code;
    }

    @Override
    public String toString() {
        return label;
    }
}
