package GUI;

public class Cook extends Employee{
    private double salary;
    private double taxRate;

    public Cook(String name, int ID,double salary){
        super(name,ID);
        this.salary = salary;
        this.taxRate = 0.18;
    }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double taxRate) { this.taxRate = taxRate; }

    public double calculateExpense() {
        return salary*(1+this.taxRate);
    }
}
