package GUI;

import java.util.ArrayList;

public class Order  {
    private ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getOrderedProducts(){

        return this.products;
    }
    public double calculateTotalPrice(){
        double result = 0;
        for(Product product: products){
            result += product.calculateExpense();
        }
        return result;
    }
    public void listOrder(){
        if(products.size()>0) {
            for (Product product : products) {
                if (product instanceof MenuProduct) {
                    MenuProduct menuproduct=(MenuProduct)product;
                    System.out.println(menuproduct.getName()+" : "+menuproduct.calculateSellingPrice());
                } else {
                    System.out.println(product.getName()+" : "+product.getSellingPrice());
                }
            }
        }else {
            System.out.println("You have not ordered anything yet!");
        }

    }
    public void addProduct(Product product){
        this.products.add(product);

    }
}
