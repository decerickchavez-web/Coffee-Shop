package com.teamdreamcafe.ui;

import com.teamdreamcafe.model.AddOn;
import com.teamdreamcafe.model.Category;
import com.teamdreamcafe.model.Drink;
import com.teamdreamcafe.model.Order;
import com.teamdreamcafe.model.OrderItem;
import com.teamdreamcafe.model.Size;
import com.teamdreamcafe.service.MenuCatalog;
import com.teamdreamcafe.service.OrderService;
import com.teamdreamcafe.util.Money;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * The order-taking screen: browse the menu on the left, build an order on the
 * right.
 */
public class PosPanel extends JPanel {

    private final OrderService orders;

    private final DefaultListModel<Drink> drinkModel = new DefaultListModel<>();
    private final JList<Drink> drinkList = new JList<>(drinkModel);
    private final JTextField searchField = new JTextField();
    private final JComboBox<String> categoryBox = new JComboBox<>(categoryChoices());
    private final ButtonGroup sizeGroup = new ButtonGroup();
    private final JRadioButton mediumButton = new JRadioButton("Medium", true);
    private final JRadioButton largeButton = new JRadioButton("Large");
    private final JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
    private final List<JCheckBox> addOnBoxes = new ArrayList<>();

    private final OrderTableModel tableModel = new OrderTableModel();
    private final JTable orderTable = new JTable();

    private final JLabel orderNumberLabel = new JLabel(" ");
    private final JLabel subtotalValue = new JLabel();
    private final JLabel discountValue = new JLabel();
    private final JLabel totalValue = new JLabel();
    private final JLabel feedback = new JLabel(" ");

    private final FlatButton removeButton = new FlatButton("Remove", Theme.PARCHMENT, Theme.BONE, Theme.MOCHA);
    private final FlatButton clearButton = new FlatButton("Clear", Theme.PARCHMENT, Theme.BONE, Theme.DANGER);
    private final FlatButton checkoutButton = new FlatButton("Checkout", Theme.BONE, Theme.BONE, Theme.MUTED);

    public PosPanel(OrderService orders) {
        if (orders == null) {
            throw new IllegalArgumentException("An order service is required.");
        }
        this.orders = orders;

        setLayout(new BorderLayout(16, 0));
        setOpaque(false);

        configureOrderTable();

        add(buildMenuCard(), BorderLayout.CENTER);
        add(buildOrderCard(), BorderLayout.EAST);

        applyFilter();
        refreshOrderView();
        feedback.setText("Pick a drink, choose a size, then send it to the order.");
        feedback.setForeground(Theme.MUTED);
    }

    // ------------------------------------------------------------------
    // Menu side
    // ------------------------------------------------------------------

    private JPanel buildMenuCard() {
        JPanel card = Theme.card();
        card.setLayout(new BorderLayout(0, 14));
        card.add(buildMenuHeader(), BorderLayout.NORTH);
        card.add(buildBrowser(), BorderLayout.CENTER);
        card.add(buildCustomizer(), BorderLayout.SOUTH);
        return card;
    }

    private JComponent buildMenuHeader() {
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Order Taking");
        title.setFont(Theme.H1);
        title.setForeground(Theme.INK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Choose a drink from the menu, set the cup size and extras, then add it to the order.");
        subtitle.setFont(Theme.SMALL);
        subtitle.setForeground(Theme.MUTED);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(4));
        header.add(subtitle);
        return header;
    }

    private JComponent buildBrowser() {
        JPanel browser = new JPanel(new BorderLayout(0, 10));
        browser.setOpaque(false);

        searchField.setToolTipText("Search a drink by name");
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                applyFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                applyFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                applyFilter();
            }
        });

        categoryBox.addActionListener(event -> applyFilter());
        categoryBox.setPreferredSize(new Dimension(210, 30));

        JPanel filters = new JPanel(new BorderLayout(10, 0));
        filters.setOpaque(false);
        filters.add(searchField, BorderLayout.CENTER);
        filters.add(categoryBox, BorderLayout.EAST);
        browser.add(filters, BorderLayout.NORTH);

        drinkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        drinkList.setCellRenderer(new DrinkRenderer());
        drinkList.setFixedCellHeight(52);
        drinkList.setBackground(Color.WHITE);
        drinkList.setBorder(new EmptyBorder(4, 4, 4, 4));
        drinkList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    addSelectedDrink();
                }
            }
        });

        JScrollPane scroll = new JScrollPane(drinkList);
        scroll.setBorder(BorderFactory.createLineBorder(Theme.LATTE));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        browser.add(scroll, BorderLayout.CENTER);
        return browser;
    }

    private JComponent buildCustomizer() {
        JPanel customizer = new JPanel(new BorderLayout(12, 10));
        customizer.setOpaque(false);

        JPanel sizeRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sizeRow.setOpaque(false);
        sizeRow.add(sectionLabel("SIZE"));
        mediumButton.setOpaque(false);
        largeButton.setOpaque(false);
        sizeGroup.add(mediumButton);
        sizeGroup.add(largeButton);
        sizeRow.add(mediumButton);
        sizeRow.add(largeButton);
        sizeRow.add(Box.createHorizontalStrut(12));
        sizeRow.add(sectionLabel("QTY"));
        qtySpinner.setPreferredSize(new Dimension(64, 28));
        sizeRow.add(qtySpinner);
        customizer.add(sizeRow, BorderLayout.NORTH);

        JPanel addOnBlock = new JPanel(new BorderLayout(0, 4));
        addOnBlock.setOpaque(false);
        addOnBlock.add(sectionLabel("ADD-ONS"), BorderLayout.NORTH);

        JPanel addOnGrid = new JPanel(new GridLayout(0, 2, 8, 2));
        addOnGrid.setOpaque(false);
        for (AddOn addOn : MenuCatalog.addOns()) {
            JCheckBox box = new JCheckBox(addOn.name() + "  +" + Money.compact(addOn.price()));
            box.setOpaque(false);
            box.setFont(Theme.SMALL);
            box.setForeground(Theme.INK);
            addOnBoxes.add(box);
            addOnGrid.add(box);
        }
        addOnBlock.add(addOnGrid, BorderLayout.CENTER);
        customizer.add(addOnBlock, BorderLayout.CENTER);

        FlatButton addButton = new FlatButton("Add to Order", Theme.MATCHA, new Color(0x6E, 0x93, 0x4C), Color.WHITE);
        addButton.setPreferredSize(new Dimension(150, 38));
        addButton.addActionListener(event -> addSelectedDrink());

        feedback.setFont(Theme.SMALL);

        JPanel actions = new JPanel(new BorderLayout(10, 0));
        actions.setOpaque(false);
        actions.add(feedback, BorderLayout.CENTER);
        actions.add(addButton, BorderLayout.EAST);
        customizer.add(actions, BorderLayout.SOUTH);
        return customizer;
    }

    private void applyFilter() {
        String keyword = searchField.getText();
        int index = categoryBox.getSelectedIndex();
        Category category = index <= 0 ? null : Category.values()[index - 1];

        List<Drink> matches = MenuCatalog.search(keyword, category);
        Drink previouslySelected = drinkList.getSelectedValue();

        drinkModel.clear();
        for (Drink drink : matches) {
            drinkModel.addElement(drink);
        }

        if (previouslySelected != null && matches.contains(previouslySelected)) {
            drinkList.setSelectedValue(previouslySelected, true);
        } else if (!drinkModel.isEmpty()) {
            drinkList.setSelectedIndex(0);
        }

        if (matches.isEmpty()) {
            setFeedback("No drinks match your search.", true);
        }
    }

    private void addSelectedDrink() {
        Drink drink = drinkList.getSelectedValue();
        if (drink == null) {
            setFeedback("Pick a drink from the list first.", true);
            return;
        }

        Size size = mediumButton.isSelected() ? Size.MEDIUM : Size.LARGE;
        int quantity = (Integer) qtySpinner.getValue();

        try {
            orders.addToOrder(drink, size, selectedAddOns(), quantity);
        } catch (IllegalArgumentException exception) {
            setFeedback(exception.getMessage(), true);
            return;
        }

        refreshOrderView();
        setFeedback("Added " + quantity + " x " + drink.name() + " (" + size.label() + ").", false);
        qtySpinner.setValue(1);
    }

    private List<AddOn> selectedAddOns() {
        List<AddOn> selected = new ArrayList<>();
        List<AddOn> catalog = MenuCatalog.addOns();
        for (int i = 0; i < addOnBoxes.size(); i++) {
            if (addOnBoxes.get(i).isSelected()) {
                selected.add(catalog.get(i));
            }
        }
        return selected;
    }

    // ------------------------------------------------------------------
    // Order side
    // ------------------------------------------------------------------

    private void configureOrderTable() {
        orderTable.setModel(tableModel);
        orderTable.setRowHeight(30);
        orderTable.setShowVerticalLines(false);
        orderTable.setShowHorizontalLines(true);
        orderTable.setGridColor(Theme.PARCHMENT);
        orderTable.setIntercellSpacing(new Dimension(0, 0));
        orderTable.setFillsViewportHeight(true);
        orderTable.setBackground(Color.WHITE);
        orderTable.setSelectionBackground(Theme.PARCHMENT);
        orderTable.setSelectionForeground(Theme.INK);
        orderTable.setRowSelectionAllowed(true);

        JTableHeader header = orderTable.getTableHeader();
        header.setReorderingAllowed(false);
        header.setBackground(Theme.PARCHMENT);
        header.setForeground(Theme.MOCHA);
        header.setFont(Theme.TINY_BOLD);

        DefaultTableCellRenderer rightAligned = new DefaultTableCellRenderer();
        rightAligned.setHorizontalAlignment(SwingConstants.RIGHT);
        orderTable.getColumnModel().getColumn(2).setCellRenderer(rightAligned);
        orderTable.getColumnModel().getColumn(3).setCellRenderer(rightAligned);
        orderTable.getColumnModel().getColumn(4).setCellRenderer(rightAligned);

        orderTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        orderTable.getColumnModel().getColumn(1).setPreferredWidth(110);
        orderTable.getColumnModel().getColumn(2).setPreferredWidth(45);
        orderTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        orderTable.getColumnModel().getColumn(4).setPreferredWidth(85);
    }

    private JPanel buildOrderCard() {
        JPanel card = Theme.card();
        card.setLayout(new BorderLayout(0, 12));
        card.setPreferredSize(new Dimension(430, 0));

        JPanel header = new JPanel(new BorderLayout(0, 2));
        header.setOpaque(false);
        JLabel title = new JLabel("Current Order");
        title.setFont(Theme.H1);
        title.setForeground(Theme.INK);
        orderNumberLabel.setFont(Theme.SMALL);
        orderNumberLabel.setForeground(Theme.MUTED);
        header.add(title, BorderLayout.NORTH);
        header.add(orderNumberLabel, BorderLayout.SOUTH);
        card.add(header, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(orderTable);
        scroll.setBorder(BorderFactory.createLineBorder(Theme.LATTE));
        scroll.getViewport().setBackground(Color.WHITE);
        card.add(scroll, BorderLayout.CENTER);
        card.add(buildTotals(), BorderLayout.SOUTH);
        return card;
    }

    private JComponent buildTotals() {
        JPanel totals = new JPanel(new GridLayout(3, 2, 10, 6));
        totals.setOpaque(false);
        totals.add(sectionLabel("Subtotal"));
        subtotalValue.setHorizontalAlignment(SwingConstants.RIGHT);
        subtotalValue.setFont(Theme.BODY);
        subtotalValue.setForeground(Theme.INK);
        totals.add(subtotalValue);

        totals.add(sectionLabel("Discount"));
        discountValue.setHorizontalAlignment(SwingConstants.RIGHT);
        discountValue.setFont(Theme.BODY);
        discountValue.setForeground(Theme.INK);
        totals.add(discountValue);

        JLabel totalLabel = new JLabel("TOTAL");
        totalLabel.setFont(Theme.H2);
        totalLabel.setForeground(Theme.INK);
        totals.add(totalLabel);
        totalValue.setHorizontalAlignment(SwingConstants.RIGHT);
        totalValue.setFont(Theme.MONEY);
        totalValue.setForeground(Theme.MATCHA);
        totals.add(totalValue);

        removeButton.setPreferredSize(new Dimension(0, 34));
        clearButton.setPreferredSize(new Dimension(0, 34));
        checkoutButton.setPreferredSize(new Dimension(0, 34));
        checkoutButton.setEnabled(false);
        checkoutButton.setToolTipText("Payment and receipt printing are added in the next build step.");
        removeButton.addActionListener(event -> removeSelectedLine());
        clearButton.addActionListener(event -> clearOrder());

        JPanel buttons = new JPanel(new GridLayout(1, 3, 8, 0));
        buttons.setOpaque(false);
        buttons.add(removeButton);
        buttons.add(clearButton);
        buttons.add(checkoutButton);

        JLabel hint = new JLabel("Tip: double-click a drink to add it instantly.");
        hint.setFont(Theme.SMALL);
        hint.setForeground(Theme.MUTED);

        JPanel south = new JPanel();
        south.setOpaque(false);
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
        totals.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttons.setAlignmentX(Component.LEFT_ALIGNMENT);
        hint.setAlignmentX(Component.LEFT_ALIGNMENT);
        south.add(totals);
        south.add(Box.createVerticalStrut(12));
        south.add(buttons);
        south.add(Box.createVerticalStrut(8));
        south.add(hint);
        return south;
    }

    private void removeSelectedLine() {
        int row = orderTable.getSelectedRow();
        if (row < 0) {
            setFeedback("Select a line in the order first.", true);
            return;
        }
        String removed = orders.currentOrder().items().get(row).drink().name();
        orders.removeItem(row);
        refreshOrderView();
        setFeedback("Removed " + removed + " from the order.", false);
    }

    private void clearOrder() {
        if (orders.currentOrder().isEmpty()) {
            setFeedback("The order is already empty.", true);
            return;
        }
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Clear every line in the current order?",
                "Clear order",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }
        Order fresh = orders.startNewOrder();
        refreshOrderView();
        setFeedback("Started a new order: " + fresh.reference() + ".", false);
    }

    private void refreshOrderView() {
        tableModel.fireTableDataChanged();
        Order order = orders.currentOrder();

        orderNumberLabel.setText("Order " + order.reference() + "   |   " + order.totalQuantity() + " item(s)");
        subtotalValue.setText(Money.format(order.subtotal()));
        discountValue.setText("-" + Money.format(order.discountAmount()));
        totalValue.setText(Money.format(order.total()));

        boolean empty = order.isEmpty();
        removeButton.setEnabled(!empty);
        clearButton.setEnabled(!empty);
    }

    private void setFeedback(String message, boolean error) {
        feedback.setText(message);
        feedback.setForeground(error ? Theme.DANGER : Theme.MATCHA);
    }

    private static JLabel sectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Theme.TINY_BOLD);
        label.setForeground(Theme.MUTED);
        return label;
    }

    private static String[] categoryChoices() {
        Category[] categories = Category.values();
        String[] choices = new String[categories.length + 1];
        choices[0] = "All categories";
        for (int i = 0; i < categories.length; i++) {
            choices[i + 1] = categories[i].displayName();
        }
        return choices;
    }

    /** Row model for the current order. */
    private class OrderTableModel extends AbstractTableModel {

        private static final String[] COLUMNS = {"Item", "Add-ons", "Size", "Qty", "Amount"};

        @Override
        public int getRowCount() {
            return orders.currentOrder().items().size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            OrderItem item = orders.currentOrder().items().get(rowIndex);
            return switch (columnIndex) {
                case 0 -> item.drink().name();
                case 1 -> item.addOnSummary();
                case 2 -> item.size().code();
                case 3 -> item.quantity();
                default -> Money.format(item.lineTotal());
            };
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }

    /** Two-line menu row: drink name on the left, both prices on the right. */
    private static class DrinkRenderer extends JPanel implements ListCellRenderer<Drink> {

        private final JLabel nameLabel = new JLabel();
        private final JLabel priceLabel = new JLabel();

        DrinkRenderer() {
            setLayout(new BorderLayout(10, 0));
            setBorder(new EmptyBorder(6, 12, 6, 12));
            nameLabel.setFont(Theme.BODY_BOLD);
            priceLabel.setFont(Theme.SMALL);
            add(nameLabel, BorderLayout.WEST);
            add(priceLabel, BorderLayout.EAST);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Drink> list, Drink drink,
                int index, boolean isSelected, boolean cellHasFocus) {
            nameLabel.setText(drink.name());
            priceLabel.setText("M " + Money.compact(drink.priceFor(Size.MEDIUM))
                    + "   |   L " + Money.compact(drink.priceFor(Size.LARGE)));

            nameLabel.setForeground(isSelected ? Color.WHITE : Theme.INK);
            priceLabel.setForeground(isSelected ? Theme.BONE : Theme.MUTED);
            setBackground(isSelected ? Theme.MOCHA : Color.WHITE);
            return this;
        }
    }
}
