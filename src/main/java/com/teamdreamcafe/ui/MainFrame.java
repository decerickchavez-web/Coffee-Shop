package com.teamdreamcafe.ui;

import com.teamdreamcafe.service.OrderService;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Main window: the espresso navigation rail on the left and the active screen on
 * the right. Screens are swapped with a {@link CardLayout}, so they stay alive
 * and navigation is instant.
 */
public class MainFrame extends JFrame {

    private final CardLayout cards = new CardLayout();
    private final JPanel content = new JPanel(cards);
    private final NavigationRail rail;

    public MainFrame(OrderService orders) {
        super("Team Dream Cafe  \u2013  Coffee Shop Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1080, 720));
        setSize(1280, 820);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        rail = new NavigationRail(this::showScreen);
        registerScreens(orders);

        add(rail, BorderLayout.WEST);

        JPanel stage = new JPanel(new BorderLayout());
        stage.setBackground(Theme.CREAM);
        stage.setBorder(new EmptyBorder(18, 18, 18, 18));
        stage.add(content, BorderLayout.CENTER);
        add(stage, BorderLayout.CENTER);

        showScreen("order");
    }

    private void registerScreens(OrderService orders) {
        content.setOpaque(false);
        content.add(new PosPanel(orders), "order");
        content.add(new PlaceholderPanel(
                "Order History",
                "Look up past transactions, reprint a receipt or void an order.",
                "Order list with date and reference filters",
                "Receipt preview and reprint",
                "Void and refund with an audit note"), "history");
        content.add(new PlaceholderPanel(
                "Inventory",
                "Track cups, beans, syrups and add-on stocks, and get low-stock warnings.",
                "Stock levels for ingredients and add-ons",
                "Stock deduction after every completed order",
                "Low-stock and restock notifications"), "inventory");
        content.add(new PlaceholderPanel(
                "Sales Report",
                "See how much the shop sold today, this week and per drink.",
                "Daily and weekly totals",
                "Best-selling drinks",
                "Sales breakdown per category and size"), "reports");
        content.add(new AboutPanel(), "about");
    }

    private void showScreen(String screen) {
        cards.show(content, screen);
        rail.setActive(screen);
    }
}
