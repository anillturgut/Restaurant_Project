package GUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class OrderPanel extends JPanel {

    private static final long serialVersionUID = 1878221131011005130L;

    public OrderPanel(Restaurant restaurant) {
        super(new BorderLayout());

        JPanel orderPanel = new JPanel(new BorderLayout()); //main panel, shown when neworder button is pressed
        orderPanel.setVisible(false);
        add(orderPanel, BorderLayout.CENTER);

        OrderTableModel orderTableModel = new OrderTableModel(); // table model
        JTable orderTable = new JTable(orderTableModel); //order table


        JPanel initialPanel = new JPanel(); // hidden when neworder button pressed
        {
            add(initialPanel, BorderLayout.NORTH);

            JButton newOrderButton = new JButton("New Order");
            newOrderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Waiter w = restaurant.assignWaiter();
                    orderTableModel.setWaiter(w);

                    JOptionPane.showMessageDialog(orderPanel,
                            "Hi, I am " + w.getName() + ".\n"
                                    + "What would you like to order?");

                    orderPanel.setVisible(true);
                    initialPanel.setVisible(false);
                }
            });
            initialPanel.add(newOrderButton);
        }

        {	// Addproduct panel
            JPanel addProductPanel = new JPanel(new GridLayout(5, 2));
            addProductPanel.setBorder(BorderFactory.createTitledBorder("Add product"));
            orderPanel.add(addProductPanel, BorderLayout.CENTER);

            JLabel priceLabel = new JLabel();

            ArrayList<Product> products = restaurant.getProducts();
            JComboBox<Product> productCbx = // combobox initialized with all products
                    new JComboBox<Product>(products.toArray(new Product[products.size()]));
            productCbx.setSelectedItem(null); //no selection
            productCbx.addActionListener(new ActionListener() { // update price label
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    priceLabel.setText(((Product) productCbx.getSelectedItem()).getSellingPrice() + " TL");
                }
            });

            JSpinner countSpinner = new JSpinner();

            JButton addButton = new JButton("Add");
            addButton.addActionListener(new ActionListener() { // add product to table
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    int count = Integer.parseInt(countSpinner.getValue().toString());
                    if (count == 0) return; //nothing if count == 0

                    Product p = (Product) productCbx.getSelectedItem();
                    orderTableModel.addProduct(p, count);
                }
            });

            addProductPanel.add(new JLabel("Product:"));
            addProductPanel.add(productCbx);
            addProductPanel.add(new JLabel("Count:"));
            addProductPanel.add(countSpinner);
            addProductPanel.add(new JLabel("Price:"));
            addProductPanel.add(priceLabel);
            addProductPanel.add(new JLabel()); //empty grid
            addProductPanel.add(addButton);
        }

        {	// Current order Panel
            JPanel currentOrderPanel = new JPanel(new BorderLayout());
            currentOrderPanel.setBorder(BorderFactory.createTitledBorder("Current Order"));
            orderPanel.add(currentOrderPanel, BorderLayout.SOUTH);

            JScrollPane scrollPane = new JScrollPane(orderTable); //table container
            scrollPane.setPreferredSize(new Dimension(0, 250));
            currentOrderPanel.add(scrollPane, BorderLayout.CENTER);

            JButton finalizeButton = new JButton("Finalize");
            finalizeButton.addActionListener(new ActionListener() { // finalize
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JOptionPane.showMessageDialog(orderPanel,
                            "Your order is completed.\nTotal price is: "
                                    + orderTableModel.calculateTotalPrice());

                    orderTableModel.finalize();

                    initialPanel.setVisible(true); //switch panels
                    orderPanel.setVisible(false);
                }
            });
            currentOrderPanel.add(finalizeButton, BorderLayout.SOUTH);

        }

    }


    /*
     * Table model for Order table
     */
    static class OrderTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 1757493541422792423L;

        private static final String[] columns = {"Product name", "Count", "Price"};

        private Order order = new Order();

        private List<TableProduct> products = new ArrayList<>();

        private Waiter waiter;

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int i) {
            return columns[i];
        }

        @Override
        public int getRowCount() {
            return products.size();
        }

        @Override
        public Object getValueAt(int row, int col) {
            if (col == 0) return products.get(row).getName();
            if (col == 1) return products.get(row).getCount();
            if (col == 2) return products.get(row).getPrice();
            return null;
        }

        void addProduct(Product p, int count) {
            order.addProduct(p);
            products.add(new TableProduct(p, count));
            fireTableRowsInserted(
                    products.size() - 1,
                    products.size() - 1);
        }

        public Order getOrder() {
            return order;
        }

        public void setWaiter(Waiter waiter) {
            this.waiter = waiter;
        }

        double calculateTotalPrice() {
            double total = 0;
            for (TableProduct p: products) {
                total += p.getPrice();
            }
            return total;
        }

        public void finalize() {
            waiter.createOrder(order);
            order = new Order(); //clear order
            products.clear(); //clear table
            fireTableDataChanged();
        }
    }

    static class TableProduct {
        private String name;
        private double price;
        private int count;

        public TableProduct(Product p, int count) {
            this.name = p.getName();
            this.price = p.getSellingPrice() * count;
            this.count = count;
        }

        public String getName() {
            return name;
        }
        public double getPrice() {
            return price;
        }
        public int getCount() {
            return count;
        }
    }
}
