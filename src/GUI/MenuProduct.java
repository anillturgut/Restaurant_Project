package GUI;

import java.util.ArrayList;

public class MenuProduct extends Product{
    private ArrayList<Product> products;

    public MenuProduct(String name, ArrayList<Product> products) {
        super(name);
        this.products = products;
        super.setSellingPrice(this.calculateSellingPrice());
    }
    public ArrayList<Product> getProducts() { return products; }
    public void setProducts(ArrayList<Product> products) { this.products = products; }

    public double calculateSellingPrice(){
        double totalPrice = 0;
        for(Product product : products){
            if(product instanceof MainDish){
                totalPrice += ((MainDish)product).getSellingPrice()*0.9;
            }else if (product instanceof Dessert){
                totalPrice += ((Dessert)product).getSellingPrice()*0.8;
            }else if(product instanceof Beverage){
                totalPrice += ((Beverage)product).getSellingPrice()*0.5;
            }
        }
        return totalPrice;
    }

    public double calculateExpense() {
        double expense = 0;
        for(Product product : products){
            expense += product.calculateExpense();
        }
        return expense;
    }
}
