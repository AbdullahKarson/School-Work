package ca.nscc;

public class Student extends Person {
    private int year;
    private double fee = 2900.00;

    Student(String name, String address, int year) {
        super(name, address);
        this.year = year;
        this.fee = fee + (year * 100.00);
    }

    double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return ". name: " + getName() + ", address: " + getAddress() + ", year: " + year + ", fee: $" + String.format("%.2f",fee) + "\n";
    }
}
