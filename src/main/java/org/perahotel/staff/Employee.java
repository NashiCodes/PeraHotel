package org.perahotel.staff;

import org.perahotel.models.Reservation;
import org.perahotel.shared.Request;

public abstract class Employee {
    protected double Salary = 0;
    protected double Bonus = 0;
    protected String Name = "";
    protected Employee next;

    protected abstract double calculateSalary();

    public double getBonus() {
        return Bonus;
    }

    public void setBonus(double bonus) {
        this.Bonus = bonus;
    }

    public abstract boolean validateRequest(Request request);

    public void setNext(Employee next) {
        this.next = next;
    }

    public double getSalary() {
        return this.calculateSalary();
    }

    public void setSalary(double salary) {
        this.Salary = salary;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    protected abstract String getExtraInfo();

    public String getInfo() {
        return "Name: " + this.getName() + "\n" +
                "Salary: " + this.calculateSalary() + "\n" +
                "Bonus: " + this.getBonus() + "\n" +
                this.getExtraInfo();
    }

    public String requestHandler(Request request, Reservation reservation) {
        if (this.validateRequest(request)) {
            return this.resolveRequest(request, reservation);
        } else if (this.next != null) {
            return this.next.requestHandler(request, reservation);
        } else {
            return "No employee can handle this request";
        }
    }

    public abstract String resolveRequest(Request request, Reservation reservation);
}
