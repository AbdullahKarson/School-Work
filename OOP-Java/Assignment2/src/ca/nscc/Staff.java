package ca.nscc;

public class Staff extends Person {
    private int yearOfService;
    private double pay = 50000.00;

    Staff(String name, String address, int yearOfService) {
        super(name, address);
        this.yearOfService = yearOfService;
        this.pay = pay + (yearOfService * 500);
    }

     double getPay() {
        return pay;
    }

    @Override
    public String toString() {

        return ". name: " + getName() + ", address: " + getAddress() + ", years: " + yearOfService + ", pay: $" + String.format("%.2f",pay) + "\n";
    }
}
