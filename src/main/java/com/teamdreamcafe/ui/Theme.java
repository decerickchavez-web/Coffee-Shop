package com.teamdreamcafe.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Central place for the Team Dream Cafe palette, fonts and shared panel styling
 * so every screen looks consistent.
 *
 * <p>Palette: espresso brown for the navigation, warm cream for the workspace,
 * matcha green for actions and gold for accents.</p>
 */
public final class Theme {

    public static final Color ESPRESSO = new Color(0x2B, 0x1D, 0x15);
    public static final Color MOCHA = new Color(0x5A, 0x3E, 0x2C);
    public static final Color CREAM = new Color(0xF6, 0xEF, 0xE3);
    public static final Color CARD = Color.WHITE;
    public static final Color LATTE = new Color(0xE4, 0xD5, 0xBE);
    public static final Color PARCHMENT = new Color(0xF1, 0xE6, 0xD3);
    public static final Color MATCHA = new Color(0x5C, 0x7F, 0x3F);
    public static final Color GOLD = new Color(0xC9, 0x9F, 0x62);
    public static final Color INK = new Color(0x2A, 0x21, 0x1B);
    public static final Color MUTED = new Color(0x86, 0x77, 0x68);
    public static final Color DANGER = new Color(0xA6, 0x38, 0x2A);
    public static final Color BONE = new Color(0xE9, 0xDC, 0xC8);

    public static final Font H1 = new Font(Font.SERIF, Font.BOLD, 24);
    public static final Font H2 = new Font(Font.SERIF, Font.BOLD, 17);
    public static final Font BODY = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
    public static final Font BODY_BOLD = new Font(Font.SANS_SERIF, Font.BOLD, 13);
    public static final Font SMALL = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    public static final Font TINY_BOLD = new Font(Font.SANS_SERIF, Font.BOLD, 11);
    public static final Font MONEY = new Font(Font.SERIF, Font.BOLD, 22);

    private Theme() {
        // Utility class.
    }

    /** Applies the global look and feel and shared font defaults. */
    public static void install() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
            // Keep the platform default look and feel.
        }
        UIManager.put("Panel.font", BODY);
        UIManager.put("Label.font", BODY);
        UIManager.put("Button.font", BODY_BOLD);
        UIManager.put("CheckBox.font", SMALL);
        UIManager.put("RadioButton.font", BODY);
        UIManager.put("TextField.font", BODY);
        UIManager.put("ComboBox.font", BODY);
        UIManager.put("List.font", BODY);
        UIManager.put("Table.font", BODY);
        UIManager.put("TableHeader.font", TINY_BOLD);
        UIManager.put("ToolTip.font", SMALL);
        UIManager.put("ToolTip.background", PARCHMENT);
        UIManager.put("ToolTip.foreground", INK);
        UIManager.put("OptionPane.messageFont", BODY);
        UIManager.put("OptionPane.buttonFont", BODY_BOLD);
        UIManager.put("Spinner.font", BODY);
    }

    /** White rounded card with a latte border and inner padding. */
    public static JPanel card() {
        JPanel panel = new JPanel();
        panel.setBackground(CARD);
        panel.setBorder(cardBorder());
        return panel;
    }

    public static Border cardBorder() {
        return BorderFactory.createCompoundBorder(
                new LineBorder(LATTE, 1, true),
                new EmptyBorder(18, 18, 18, 18));
    }

    /** Transparent panel, handy for layout wrappers. */
    public static JPanel transparent() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }
}
