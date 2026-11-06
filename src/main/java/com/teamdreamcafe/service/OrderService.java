package com.teamdreamcafe.service;

import com.teamdreamcafe.model.AddOn;
import com.teamdreamcafe.model.Drink;
import com.teamdreamcafe.model.Order;
import com.teamdreamcafe.model.OrderItem;
import com.teamdreamcafe.model.Size;
import java.util.List;

/**
 * Coordinates the order that is currently being taken at the counter.
 *
 * <p>The service owns order numbering, so the user interface never needs to
 * know how references are generated.</p>
 */
public class OrderService {

    private static final int FIRST_ORDER_NUMBER = 1001;

    private int nextOrderNumber = FIRST_ORDER_NUMBER;
    private Order currentOrder = new Order(nextOrderNumber);

    public Order currentOrder() {
        return currentOrder;
    }

    /**
     * Adds a drink to the current order.
     *
     * @return the line that was created or merged into
     * @throws IllegalArgumentException when the input is invalid
     */
    public OrderItem addToOrder(Drink drink, Size size, List<AddOn> addOns, int quantity) {
        OrderItem item = new OrderItem(drink, size, addOns, quantity);
        currentOrder.addItem(item);
        return item;
    }

    public void removeItem(int index) {
        currentOrder.removeItem(index);
    }

    /** Discards the current order and starts a new one with the next number. */
    public Order startNewOrder() {
        nextOrderNumber++;
        currentOrder = new Order(nextOrderNumber);
        return currentOrder;
    }

    public int nextOrderNumber() {
        return nextOrderNumber;
    }
}
