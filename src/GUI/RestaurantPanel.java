package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RestaurantPanel extends JPanel {

    private static final long serialVersionUID = -8158366234675603354L;

    private final Restaurant restaurant;

    public RestaurantPanel(Restaurant r) {
        super(new BorderLayout());

        this.restaurant = r;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(buttonPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(); // this is where all panels are
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        JPanel listEmployeePanel = new JPanel();
        listEmployeePanel.setLayout(new BoxLayout(listEmployeePanel, BoxLayout.Y_AXIS));
        mainPanel.add(listEmployeePanel);

        JButton listEmployeesButton = new JButton("List Employees");
        buttonPanel.add(listEmployeesButton);
        listEmployeesButton.addActionListener(new ActionListener() { // renders employee list
            @Override
            public void actionPerformed(ActionEvent arg0) {
                clearPanel(mainPanel);
                listEmployeePanel.setVisible(true);
                listEmployeePanel.removeAll();
                showEmployees(restaurant.getEmployees(), listEmployeePanel); //helper method
            }
        });


        JPanel addCookPanel = cookPanel();
        mainPanel.add(addCookPanel);
        JButton addCookButton = new JButton("Add Cook");
        buttonPanel.add(addCookButton);
        addCookButton.addActionListener(new ActionListener() { //shows add cook panel
            @Override
            public void actionPerformed(ActionEvent arg0) {
                clearPanel(mainPanel);
                addCookPanel.setVisible(true);
            }
        });


        JPanel addWaiterPanel = waiterPanel();
        mainPanel.add(addWaiterPanel);
        JButton addWaiterButton = new JButton("Add Waiter");
        buttonPanel.add(addWaiterButton);
        addWaiterButton.addActionListener(new ActionListener() { //shows add waiter panel
            @Override
            public void actionPerformed(ActionEvent arg0) {
                clearPanel(mainPanel);
                addWaiterPanel.setVisible(true);
            }
        });

        JPanel expensesPanel = new JPanel(new GridLayout(3, 2));
        expensesPanel.setMaximumSize(new Dimension(800, 150));
        expensesPanel.setBorder(BorderFactory.createTitledBorder("Calculate expenses"));
        mainPanel.add(expensesPanel);

        JLabel expenses = new JLabel();
        JLabel revenue = new JLabel();
        JLabel profit = new JLabel();

        expensesPanel.add(new JLabel("Expenses:"));
        expensesPanel.add(expenses);
        expensesPanel.add(new JLabel("Revenue:"));
        expensesPanel.add(revenue);
        expensesPanel.add(new JLabel("Profit:"));
        expensesPanel.add(profit);

        JButton calculateExpButton = new JButton("Calculate expenses");
        buttonPanel.add(calculateExpButton);
        calculateExpButton.addActionListener(new ActionListener() { //renders expenses
            @Override
            public void actionPerformed(ActionEvent arg0) {
                clearPanel(mainPanel);
                expensesPanel.setVisible(true);

                expenses.setText(restaurant.calculateExpenses() + " TL");
                revenue.setText(restaurant.calculateRevenue() + " TL");
                profit.setText(restaurant.calculateRevenue() - restaurant.calculateExpenses() + " TL");
            }
        });

        clearPanel(mainPanel); // sets all panels invisible at start
    }


    private void showEmployees(ArrayList<Employee> listEmployees, JPanel listEmployeePanel) {
        JPanel header = new JPanel(new GridLayout(1, 3));
        header.setMaximumSize(new Dimension(800,45));
        header.setForeground(Color.red);

        JLabel idLabel = new JLabel("ID");
        idLabel.setForeground(Color.RED);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.RED);

        JLabel jobLabel = new JLabel("Job");
        jobLabel.setForeground(Color.RED);

        header.add(idLabel);
        header.add(nameLabel);
        header.add(jobLabel);

        listEmployeePanel.add(header);

        for (Employee e: listEmployees) {
            JPanel row = new JPanel(new GridLayout(1, 3));
            row.setMaximumSize(new Dimension(800,45));
            row.add(new JLabel(e.getID() + ""));
            row.add(new JLabel(e.getName()));
            row.add(new JLabel(e.getClass().getSimpleName()));

            listEmployeePanel.add(row);
        }

        listEmployeePanel.add(Box.createVerticalGlue());

    }

    private JPanel cookPanel() {
        JPanel addCookPanel = new JPanel(new GridLayout(3, 2));
        addCookPanel.setMaximumSize(new Dimension(800, 120));
        addCookPanel.setBorder(BorderFactory.createTitledBorder("Add Cook"));

        JTextField nameField = new JTextField();
        JTextField salaryField = new JTextField();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                restaurant.addCook(nameField.getText(), Double.parseDouble(salaryField.getText()));
                JOptionPane.showMessageDialog(addCookPanel,
                        "Cook added successfully");

                nameField.setText("");
                salaryField.setText("");
            }
        });

        addCookPanel.add(new JLabel("Name"));
        addCookPanel.add(nameField);
        addCookPanel.add(new JLabel("Salary"));
        addCookPanel.add(salaryField);
        addCookPanel.add(new JPanel());
        addCookPanel.add(addButton);
        return addCookPanel;
    }

    private JPanel waiterPanel() {
        JPanel addWaiterPanel = new JPanel(new GridLayout(2, 2));
        addWaiterPanel.setMaximumSize(new Dimension(800, 80));
        addWaiterPanel.setBorder(BorderFactory.createTitledBorder("Add Waiter"));

        JTextField nameField = new JTextField();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                restaurant.addWaiter(nameField.getText());
                JOptionPane.showMessageDialog(addWaiterPanel,
                        "Waiter added successfully");

                nameField.setText("");
            }
        });

        addWaiterPanel.add(new JLabel("Name"));
        addWaiterPanel.add(nameField);
        addWaiterPanel.add(new JPanel());
        addWaiterPanel.add(addButton);
        return addWaiterPanel;
    }

    private void clearPanel(JPanel panel) { //sets all panels invisible
        for (Component c: panel.getComponents()) {
            if (c.isVisible())
                c.setVisible(false);
        }
    }
}
