package com.teamdreamcafe.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * The dark espresso navigation rail on the left of the main window.
 *
 * <p>The rail knows nothing about the screens themselves: it simply reports the
 * key of the screen the user clicked, so it can be reused and rendered on its
 * own.</p>
 */
public class NavigationRail extends JPanel {

    private static final String[] LABELS = {
        "Order Taking", "Order History", "Inventory", "Sales Report", "About the System"
    };
    private static final String[] SCREENS = {"order", "history", "inventory", "reports", "about"};

    private final Map<String, FlatButton> buttons = new LinkedHashMap<>();

    public NavigationRail(Consumer<String> onNavigate) {
        setBackground(Theme.ESPRESSO);
        setPreferredSize(new Dimension(248, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(26, 18, 20, 18));

        JLabel brand = new JLabel("TEAM DREAM");
        brand.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        brand.setForeground(Color.WHITE);
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel brandAccent = new JLabel("C  A  F  E");
        brandAccent.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
        brandAccent.setForeground(Theme.GOLD);
        brandAccent.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tagline = new JLabel("Coffee Shop Management");
        tagline.setFont(Theme.SMALL);
        tagline.setForeground(new Color(0xC9, 0xB8, 0xA6));
        tagline.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(brand);
        add(Box.createVerticalStrut(4));
        add(brandAccent);
        add(Box.createVerticalStrut(8));
        add(tagline);
        add(Box.createVerticalStrut(18));
        add(divider());
        add(Box.createVerticalStrut(18));

        for (int i = 0; i < LABELS.length; i++) {
            addItem(LABELS[i], SCREENS[i], onNavigate);
        }

        add(Box.createVerticalGlue());
        add(divider());
        add(Box.createVerticalStrut(12));

        JLabel footer = new JLabel("<html>Build 0.1 \u2013 step 1<br/>Java Swing \u00b7 no external libraries</html>");
        footer.setFont(Theme.SMALL);
        footer.setForeground(new Color(0x9C, 0x8A, 0x79));
        footer.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(footer);
    }

    /** Highlights the button that belongs to the visible screen. */
    public void setActive(String screen) {
        buttons.forEach((key, button) -> button.setActive(key.equals(screen)));
    }

    private void addItem(String label, String screen, Consumer<String> onNavigate) {
        FlatButton button = new FlatButton(
                label,
                Theme.ESPRESSO,
                new Color(0x40, 0x2D, 0x22),
                new Color(0x35, 0x25, 0x1C),
                Theme.MATCHA,
                new Color(0xF3, 0xE8, 0xDA),
                10);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(11, 14, 11, 14));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.addActionListener(event -> onNavigate.accept(screen));

        buttons.put(screen, button);
        add(button);
        add(Box.createVerticalStrut(6));
    }

    private JPanel divider() {
        JPanel line = new JPanel();
        line.setBackground(new Color(0x46, 0x33, 0x27));
        line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        line.setPreferredSize(new Dimension(200, 1));
        line.setAlignmentX(Component.LEFT_ALIGNMENT);
        return line;
    }
}
