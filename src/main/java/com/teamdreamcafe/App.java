package com.teamdreamcafe;

import com.teamdreamcafe.service.OrderService;
import com.teamdreamcafe.ui.MainFrame;
import com.teamdreamcafe.ui.Theme;
import java.awt.EventQueue;

/**
 * Entry point of the Team Dream Cafe Coffee Shop Management System.
 *
 * <p>All work that touches Swing components is scheduled on the Event Dispatch
 * Thread (EDT), which is the correct pattern for Java desktop applications.</p>
 */
public final class App {

    private App() {
        // Utility class: never instantiated.
    }

    public static void main(String[] args) {
        Theme.install();
        EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame(new OrderService());
            frame.setVisible(true);
        });
    }
}
