package GUI;

import java.util.ArrayList;

public class Waiter extends Employee{
    private double orderRate = 0.1;
    private ArrayList<Order> ordersReceived;


    public Waiter(String name, int ID) {
        super(name, ID);
        this.ordersReceived = new ArrayList<Order>();

    }
    public double getOrderRate() { return orderRate; }
    public void setOrderRate(double orderRate) { this.orderRate = orderRate; }

    public void createOrder(Order order){

        this.ordersReceived.add(order);
    }
    public ArrayList<Order> getOrdersReceived(){

        return this.ordersReceived;
    }

    public double calculateExpense() {
        double exp = 0;
        for(Order order: ordersReceived){
            exp += order.calculateTotalPrice()*orderRate;
        }
        return exp;
    }

}
