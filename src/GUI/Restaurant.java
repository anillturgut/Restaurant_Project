package GUI;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    public Restaurant() {
        initEmployees();
        initProducts();
    }

    private void initEmployees() {

        addCook("Monica", 100);

        addWaiter("Ross");
        addWaiter("Phobe");
        addWaiter("Rachel");
    }

    private void initProducts() {

        // Parameters for Product Constructor:
        // Product Name, Selling Price, Purchase Price and Utility Cost

        products.add(new MainDish("Pizza", 6, 2, 2));
        products.add(new MainDish("Burger", 5, 1.5, 2));

        products.add(new Beverage("Coke", 2, 0.5));
        products.add(new Beverage("Lemonade", 2, 0.3));

        products.add(new Dessert("Tiramusu", 4, 1, 1));
        products.add(new Dessert("Cake", 3, 0.5, 1));
        products.add(new Dessert("Ice Cream", 3, 0.5, 0.5));

        ArrayList<Product> HGproducts = new ArrayList<>();
        HGproducts.add(new MainDish("Pizza", 6, 2, 2));
        HGproducts.add(new Beverage("Coke", 2, 0.5));
        HGproducts.add(new Dessert("Tiramusu", 4, 1, 1));
        products.add(new MenuProduct("Hunger Games Menu", HGproducts));

        ArrayList<Product> Kidsproducts = new ArrayList<>();
        Kidsproducts.add(new MainDish("Burger", 5, 1.5, 2));
        Kidsproducts.add(new Beverage("Lemonade", 2, 0.3));
        Kidsproducts.add(new Dessert("Ice Cream", 3, 0.5, 0.5));
        products.add(new MenuProduct("Kids Menu", Kidsproducts));
    }


    public void addCook(String name, double salary){
        int ID = employees.size() + 1;
        employees.add(new Cook(name,ID,salary));

    }
    public void addWaiter(String name){
        int ID = employees.size() + 1;
        employees.add(new Waiter(name,ID));
    }
    public void listEmployees(){
        for(int i = 0; i < employees.size(); i++){
            System.out.println(employees.get(i));
        }
    }
    public Waiter assignWaiter(){
        Random rand = new Random();
        int ind = rand.nextInt(employees.size());
        while(!(employees.get(ind) instanceof Waiter)){
            ind = rand.nextInt(employees.size());
        }
        return (Waiter)employees.get(ind);
    }
    public double calculateExpenses() {
        double employeeExp = 0;
        double orderExp = 0;
        for(Employee employee : employees){
            employeeExp += employee.calculateExpense();
        }
        for(int i = 0; i < employees.size(); i++){
            if(employees.get(i) instanceof Waiter){
                Waiter w = (Waiter)employees.get(i);
                for(Order order: w.getOrdersReceived()){
                    ArrayList<Product> products = order.getOrderedProducts();
                    for(Product product : products){
                        orderExp += product.calculateExpense();
                    }
                }

            }
        }
        System.out.println("Employee expense: " + employeeExp);
        System.out.println("Order expense: " + orderExp);
        return employeeExp + orderExp;
    }
    public double calculateRevenue(){
        double revenue = 0;
        for(Employee employee: employees){
            if(employee instanceof Waiter){
                Waiter w = (Waiter)employee;
                for(Order orders : w.getOrdersReceived()){
                    revenue += orders.calculateTotalPrice();
                }
            }
        }
        return revenue;
    }
    public ArrayList<Product> getProducts(){
        return products;

    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

}
