package GUI;

public abstract class Employee implements Expense{
    private String name;
    private int ID;

    public Employee(String name, int ID){
        this.name = name;
        this.ID = ID;
    }
    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }


    public String toString(){

        return "Employee " + this.ID + ": " + this.name;
    }
}
