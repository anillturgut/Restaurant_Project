package GUI;

public abstract class Product implements Expense{
    private String name;
    private double purchasePrice;
    private double sellingPrice;
    private double utilityCost;

    public Product(String name){
        this.name = name;
    }
    public Product(String name, double sellingPrice, double purchasePrice){
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
    }

    public Product(String name, double sellingPrice,double purchasePrice, double utilityCost){
        this.name = name;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.utilityCost = utilityCost;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(double purchasePrice) { this.purchasePrice = purchasePrice; }
    public double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.sellingPrice = sellingPrice; }
    public double getUtilityCost() { return utilityCost; }
    public void setUtilityCost(double utilityCost) { this.utilityCost = utilityCost; }

    public String toString(){

        return  this.name + ": " + this.sellingPrice;
    }
}
