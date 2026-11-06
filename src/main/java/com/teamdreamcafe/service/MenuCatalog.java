package com.teamdreamcafe.service;

import com.teamdreamcafe.model.AddOn;
import com.teamdreamcafe.model.Category;
import com.teamdreamcafe.model.Drink;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * The Team Dream Cafe menu: every drink with its medium and large price, plus
 * the paid add-ons.
 *
 * <p>The data is built once in a static block, so the catalogue is read-only for
 * the whole application. Prices are in Philippine pesos and follow the shop's
 * price boards.</p>
 */
public final class MenuCatalog {

    private static final List<Drink> DRINKS;
    private static final List<AddOn> ADD_ONS;

    static {
        List<Drink> drinks = new ArrayList<>();

        // ---- Matcha Series ----
        drinks.add(new Drink("MCH-01", "Matcha Latte", Category.MATCHA_SERIES, 48, 58));
        drinks.add(new Drink("MCH-02", "Milktea Matcha", Category.MATCHA_SERIES, 48, 58));
        drinks.add(new Drink("MCH-03", "Dirty Matcha", Category.MATCHA_SERIES, 58, 68));
        drinks.add(new Drink("MCH-04", "Team Dream Matcha", Category.MATCHA_SERIES, 58, 68));
        drinks.add(new Drink("MCH-05", "White Chocolate Matcha", Category.MATCHA_SERIES, 58, 68));
        drinks.add(new Drink("MCH-06", "Strawberry Matcha", Category.MATCHA_SERIES, 68, 78));
        drinks.add(new Drink("MCH-07", "Chocolate Matcha", Category.MATCHA_SERIES, 68, 78));

        // ---- Coffee Based ----
        drinks.add(new Drink("COF-01", "Americano", Category.COFFEE_BASED, 48, 58));
        drinks.add(new Drink("COF-02", "Latte", Category.COFFEE_BASED, 48, 58));
        drinks.add(new Drink("COF-03", "Hazelnut Latte", Category.COFFEE_BASED, 48, 58));
        drinks.add(new Drink("COF-04", "Vanilla Latte", Category.COFFEE_BASED, 48, 58));
        drinks.add(new Drink("COF-05", "Brown Sugar Latte", Category.COFFEE_BASED, 48, 58));
        drinks.add(new Drink("COF-06", "Team Dream Signature", Category.COFFEE_BASED, 48, 58));

        // ---- Special Coffee Based ----
        drinks.add(new Drink("SPC-01", "White Mocha", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-02", "Dark Mocha", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-03", "Caramel Latte", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-04", "Caramel Macchiato", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-05", "White Caramel Mocha", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-06", "Salted Caramel", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-07", "Spanish Latte", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-08", "Tres Leches Latte", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-09", "Einspanner Latte", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-10", "Choco Hazelnut", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-11", "Caramel Coffee Jelly", Category.SPECIAL_COFFEE, 58, 68));
        drinks.add(new Drink("SPC-12", "Blueberry Greek Latte", Category.SPECIAL_COFFEE, 58, 68));

        // ---- Milk Tea Baby ----
        drinks.add(new Drink("MTB-01", "Caramel", Category.MILK_TEA, 38, 48));
        drinks.add(new Drink("MTB-02", "Okinawa", Category.MILK_TEA, 38, 48));
        drinks.add(new Drink("MTB-03", "Wintermelon", Category.MILK_TEA, 38, 48));
        drinks.add(new Drink("MTB-04", "Brown Sugar", Category.MILK_TEA, 38, 48));
        drinks.add(new Drink("MTB-05", "Taro", Category.MILK_TEA, 48, 58));
        drinks.add(new Drink("MTB-06", "Mango", Category.MILK_TEA, 48, 58));
        drinks.add(new Drink("MTB-07", "Strawberry", Category.MILK_TEA, 48, 58));
        drinks.add(new Drink("MTB-08", "Choco Hazelnut", Category.MILK_TEA, 48, 58));
        drinks.add(new Drink("MTB-09", "Dark Chocolate", Category.MILK_TEA, 48, 58));

        // ---- Milk Based ----
        drinks.add(new Drink("MLK-01", "Taro", Category.MILK_BASED, 48, 58));
        drinks.add(new Drink("MLK-02", "Mango", Category.MILK_BASED, 48, 58));
        drinks.add(new Drink("MLK-03", "Chocolate", Category.MILK_BASED, 48, 58));
        drinks.add(new Drink("MLK-04", "Strawberry", Category.MILK_BASED, 48, 58));

        // ---- Special Milk Based ----
        drinks.add(new Drink("SML-01", "Choco Berry", Category.SPECIAL_MILK_BASED, 58, 68));
        drinks.add(new Drink("SML-02", "Mango Berry", Category.SPECIAL_MILK_BASED, 58, 68));
        drinks.add(new Drink("SML-03", "Choco Mango", Category.SPECIAL_MILK_BASED, 58, 68));
        drinks.add(new Drink("SML-04", "Brown Sugar Boba Milk", Category.SPECIAL_MILK_BASED, 58, 68));
        drinks.add(new Drink("SML-05", "Milo Dinosaur", Category.SPECIAL_MILK_BASED, 58, 68));

        // ---- Fruit Tea Series ----
        drinks.add(new Drink("FT-01", "Lemon", Category.FRUIT_TEA, 38, 48));
        drinks.add(new Drink("FT-02", "Mango", Category.FRUIT_TEA, 38, 48));
        drinks.add(new Drink("FT-03", "Lychee", Category.FRUIT_TEA, 38, 48));
        drinks.add(new Drink("FT-04", "Blueberry", Category.FRUIT_TEA, 38, 48));
        drinks.add(new Drink("FT-05", "Green Apple", Category.FRUIT_TEA, 38, 48));
        drinks.add(new Drink("FT-06", "Strawberry", Category.FRUIT_TEA, 38, 48));
        drinks.add(new Drink("FT-07", "Honey Peach", Category.FRUIT_TEA, 38, 48));
        drinks.add(new Drink("FT-08", "Passion Fruit", Category.FRUIT_TEA, 38, 48));

        // ---- Frappe Coffee Based ----
        drinks.add(new Drink("FRC-01", "Dark Mocha", Category.FRAPPE_COFFEE, 68, 78));
        drinks.add(new Drink("FRC-02", "White Mocha", Category.FRAPPE_COFFEE, 68, 78));
        drinks.add(new Drink("FRC-03", "Choco Hazelnut", Category.FRAPPE_COFFEE, 68, 78));
        drinks.add(new Drink("FRC-04", "Team Dream Signature", Category.FRAPPE_COFFEE, 68, 78));
        drinks.add(new Drink("FRC-05", "Caramel Macchiato", Category.FRAPPE_COFFEE, 68, 78));
        drinks.add(new Drink("FRC-06", "Java Chip", Category.FRAPPE_COFFEE, 78, 88));
        drinks.add(new Drink("FRC-07", "Caramel Coffee Jelly", Category.FRAPPE_COFFEE, 78, 88));

        // ---- Frappe Milk Based ----
        drinks.add(new Drink("FRM-01", "Matcha", Category.FRAPPE_MILK, 68, 78));
        drinks.add(new Drink("FRM-02", "Cookies and Cream", Category.FRAPPE_MILK, 68, 78));
        drinks.add(new Drink("FRM-03", "Chocolate", Category.FRAPPE_MILK, 78, 88));
        drinks.add(new Drink("FRM-04", "Taro", Category.FRAPPE_MILK, 78, 88));
        drinks.add(new Drink("FRM-05", "Mango", Category.FRAPPE_MILK, 78, 88));
        drinks.add(new Drink("FRM-06", "Strawberry", Category.FRAPPE_MILK, 78, 88));
        drinks.add(new Drink("FRM-07", "Milo Dinosaur", Category.FRAPPE_MILK, 78, 88));

        DRINKS = List.copyOf(drinks);

        ADD_ONS = List.of(
                new AddOn("Milk", 8),
                new AddOn("Nata", 8),
                new AddOn("Pearls", 8),
                new AddOn("Coffee Jelly", 8),
                new AddOn("Flavoured Syrup", 8),
                new AddOn("Coffee Shot", 18),
                new AddOn("Whipped Cream", 18)
        );
    }

    private MenuCatalog() {
        // Utility class.
    }

    /** All drinks, in menu order. */
    public static List<Drink> drinks() {
        return DRINKS;
    }

    /** All paid add-ons. */
    public static List<AddOn> addOns() {
        return ADD_ONS;
    }

    public static int drinkCount() {
        return DRINKS.size();
    }

    /** Drinks of one section only. */
    public static List<Drink> byCategory(Category category) {
        List<Drink> result = new ArrayList<>();
        for (Drink drink : DRINKS) {
            if (drink.category() == category) {
                result.add(drink);
            }
        }
        return result;
    }

    /**
     * Filters the menu by a keyword and, optionally, a section.
     *
     * @param keyword  matched against the drink name, case-insensitive; blank
     *                 means "every name"
     * @param category section to restrict to, or {@code null} for all sections
     */
    public static List<Drink> search(String keyword, Category category) {
        String needle = keyword == null ? "" : keyword.trim().toLowerCase(Locale.ROOT);
        List<Drink> result = new ArrayList<>();
        for (Drink drink : DRINKS) {
            if (category != null && drink.category() != category) {
                continue;
            }
            if (!needle.isEmpty() && !drink.name().toLowerCase(Locale.ROOT).contains(needle)) {
                continue;
            }
            result.add(drink);
        }
        return result;
    }

    public static Optional<Drink> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        for (Drink drink : DRINKS) {
            if (drink.id().equalsIgnoreCase(id.trim())) {
                return Optional.of(drink);
            }
        }
        return Optional.empty();
    }

    /** Looks up an add-on by name (case-insensitive). */
    public static Optional<AddOn> findAddOn(String name) {
        if (name == null) {
            return Optional.empty();
        }
        for (AddOn addOn : ADD_ONS) {
            if (addOn.name().equalsIgnoreCase(name.trim())) {
                return Optional.of(addOn);
            }
        }
        return Optional.empty();
    }

    /** Menu sections in display order, each with its drinks. */
    public static Map<Category, List<Drink>> groupedByCategory() {
        Map<Category, List<Drink>> grouped = new LinkedHashMap<>();
        for (Category category : Category.values()) {
            grouped.put(category, List.copyOf(byCategory(category)));
        }
        return Collections.unmodifiableMap(grouped);
    }
}
