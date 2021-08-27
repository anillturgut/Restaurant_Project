package GUI;

import javax.swing.*;
import java.awt.*;

public class Gui {
    public static void main(String[] args) {
        //ANIL TURGUT,S015146,CS102 PROJECT03
        Restaurant restaurant = new Restaurant();

        JFrame frame = new JFrame("Project");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 500);
        frame.setMinimumSize(new Dimension(650, 500));

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel orderPanel = new OrderPanel(restaurant);
        tabbedPane.add("Order", orderPanel);

        JPanel restaurantPanel = new RestaurantPanel(restaurant);
        tabbedPane.add("Restaurant", restaurantPanel);


        frame.add(tabbedPane);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
