package com.teamdreamcafe.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Screen shown for modules that are planned but not built yet. Keeping a real
 * screen (instead of an empty panel) makes it obvious during the presentation
 * which parts are finished and which are next.
 */
public class PlaceholderPanel extends JPanel {

    public PlaceholderPanel(String title, String subtitle, String... plannedItems) {
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel card = Theme.card();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(Theme.LATTE, 1, true),
                new EmptyBorder(26, 26, 26, 26)));

        JLabel heading = new JLabel(title);
        heading.setFont(Theme.H1);
        heading.setForeground(Theme.INK);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);
        heading.setMaximumSize(heading.getPreferredSize());

        JLabel description = new JLabel(subtitle);
        description.setFont(Theme.BODY);
        description.setForeground(Theme.MUTED);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);
        description.setMaximumSize(description.getPreferredSize());

        JLabel badge = new JLabel("  PLANNED FOR THE NEXT BUILD STEP  ");
        badge.setOpaque(true);
        badge.setBackground(Theme.PARCHMENT);
        badge.setForeground(Theme.MOCHA);
        badge.setFont(Theme.TINY_BOLD);
        badge.setBorder(new EmptyBorder(6, 10, 6, 10));
        badge.setAlignmentX(Component.LEFT_ALIGNMENT);
        badge.setMaximumSize(badge.getPreferredSize());

        card.add(heading);
        card.add(Box.createVerticalStrut(6));
        card.add(description);
        card.add(Box.createVerticalStrut(20));
        card.add(badge);
        card.add(Box.createVerticalStrut(18));

        for (String item : plannedItems) {
            JLabel line = new JLabel("\u2022  " + item);
            line.setFont(Theme.BODY);
            line.setForeground(Theme.INK);
            line.setAlignmentX(Component.LEFT_ALIGNMENT);
            line.setBorder(new EmptyBorder(3, 0, 3, 0));
            line.setMaximumSize(line.getPreferredSize());
            card.add(line);
        }

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.add(card, BorderLayout.NORTH);
        add(wrapper, BorderLayout.CENTER);
    }

    /** Convenience constructor for a single screen heading. */
    public PlaceholderPanel(String title) {
        this(title, "This module is not part of the current build.", "Nothing to show yet.");
    }

    @SuppressWarnings("unused")
    private static Font headingFont() {
        return Theme.H1;
    }
}
