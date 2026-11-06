package com.teamdreamcafe.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * Reference screen describing the system: useful during the class presentation
 * and for the written documentation.
 */
public class AboutPanel extends JPanel {

    public AboutPanel() {
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel content = new JPanel();
        content.setBackground(Theme.CARD);
        content.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(Theme.LATTE, 1, true),
                new EmptyBorder(24, 26, 26, 26)));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Team Dream Cafe  \u2013  Coffee Shop Management System");
        title.setFont(Theme.H1);
        title.setForeground(Theme.INK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tagline = new JLabel("A Java Swing desktop application for taking orders, tracking stock and reporting sales.");
        tagline.setFont(Theme.BODY);
        tagline.setForeground(Theme.MUTED);
        tagline.setAlignmentX(Component.LEFT_ALIGNMENT);

        content.add(title);
        content.add(Box.createVerticalStrut(6));
        content.add(tagline);
        content.add(Box.createVerticalStrut(22));

        JPanel columns = new JPanel(new GridLayout(1, 2, 26, 0));
        columns.setOpaque(false);
        columns.setAlignmentX(Component.LEFT_ALIGNMENT);
        columns.add(listCard("Modules", new String[]{
                "Order taking with size, quantity and add-ons",
                "Live order summary and totals",
                "Checkout, payment and receipt (next step)",
                "Inventory tracking (next step)",
                "Sales reports (next step)",
                "Data saved between sessions (next step)"
        }));
        columns.add(listCard("How it is built", new String[]{
                "Java 17, Swing, no external libraries",
                "Model: MenuItem, Drink, AddOn, OrderItem, Order",
                "Inheritance and polymorphism in pricing",
                "Strategy pattern for discounts",
                "Layered packages: model, service, ui, util",
                "Automated tests for the pricing rules"
        }));
        content.add(columns);
        content.add(Box.createVerticalStrut(22));

        JLabel teamHeading = new JLabel("TEAM DREAM CAFE - GROUP MEMBERS");
        teamHeading.setFont(Theme.TINY_BOLD);
        teamHeading.setForeground(Theme.MOCHA);
        teamHeading.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(teamHeading);
        content.add(Box.createVerticalStrut(10));
        content.add(teamBlock());

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(content, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapper);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel teamBlock() {
        JPanel grid = new JPanel(new GridLayout(2, 2, 22, 12));
        grid.setOpaque(false);
        grid.setAlignmentX(Component.LEFT_ALIGNMENT);
        grid.add(member("John Evaris", "Group creator | project lead, documentation and presentation"));
        grid.add(member("Decerick Chavez", "Menu model, pricing rules and automated tests"));
        grid.add(member("Kenneth Clier Yusores", "Order-taking screen and interface theme"));
        grid.add(member("Khyle Etolle", "Checkout, receipt, inventory and sales reports"));
        return grid;
    }

    private JPanel member(String name, String role) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(Theme.BODY_BOLD);
        nameLabel.setForeground(Theme.INK);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel roleLabel = new JLabel(role);
        roleLabel.setFont(Theme.SMALL);
        roleLabel.setForeground(Theme.MUTED);
        roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(2));
        panel.add(roleLabel);
        return panel;
    }

    private JPanel listCard(String heading, String[] lines) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(heading.toUpperCase());
        title.setFont(Theme.TINY_BOLD);
        title.setForeground(Theme.MOCHA);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(8));

        for (String line : lines) {
            JLabel label = new JLabel("\u2022  " + line);
            label.setFont(Theme.BODY);
            label.setForeground(Theme.INK);
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            label.setBorder(new EmptyBorder(2, 0, 2, 0));
            panel.add(label);
        }
        return panel;
    }
}
