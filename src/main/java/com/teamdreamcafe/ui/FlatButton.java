package com.teamdreamcafe.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ButtonModel;
import javax.swing.JButton;

/**
 * A flat, rounded button drawn by hand.
 *
 * <p>This is a small example of inheritance and custom painting: the class
 * extends {@link JButton} (so it still behaves like a normal Swing button, with
 * action listeners and keyboard support) but replaces the look with a modern
 * rounded rectangle.</p>
 */
public class FlatButton extends JButton {

    private final Color base;
    private final Color hover;
    private final Color pressed;
    private final Color activeFill;
    private final int arc;
    private boolean active;

    /** Button for normal actions, e.g. Add to Order. */
    public FlatButton(String text, Color base, Color hover, Color textColor) {
        this(text, base, hover, hover.darker(), null, textColor, 12);
    }

    /**
     * Full constructor.
     *
     * @param activeFill fill used while the button is the selected navigation
     *                   item, or {@code null} when the button is not a tab
     */
    public FlatButton(String text, Color base, Color hover, Color pressed,
                      Color activeFill, Color textColor, int arc) {
        super(text);
        this.base = base;
        this.hover = hover;
        this.pressed = pressed;
        this.activeFill = activeFill;
        this.arc = arc;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setForeground(textColor);
        setFont(Theme.BODY_BOLD);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /** Marks this button as the current screen in the navigation. */
    public void setActive(boolean active) {
        this.active = active;
        repaint();
    }

    public void setTextFont(Font font) {
        setFont(font);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ButtonModel model = getModel();
        Color fill;
        if (!isEnabled()) {
            fill = Theme.BONE;
        } else if (active && activeFill != null) {
            fill = activeFill;
        } else if (model.isPressed()) {
            fill = pressed;
        } else if (model.isRollover()) {
            fill = hover;
        } else {
            fill = base;
        }

        g2.setColor(fill);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        g2.dispose();

        super.paintComponent(graphics);
    }
}
