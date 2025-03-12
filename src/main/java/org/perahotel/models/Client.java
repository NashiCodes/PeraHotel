package org.perahotel.models;

import org.perahotel.observer.Observer;

import java.util.UUID;

public class Client extends Observer {
    private UUID clientId;
    private String name;
    private String email;
    private String phone;
    private Reservation reservation;
    private double credit;

    public Client() {
    }

    public Client(String name, String email, String phone) {
        this.clientId = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public void cancelReservation() {

    }

    public void checkOut() {
    }

    public boolean pay() {
        var amount = reservation.Cost();
        if (credit >= amount) {
            credit -= amount;
            return true;
        }
        return false;
    }

    public void addCredit(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
        this.credit += amount;
    }

    public void removeCredit(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
        this.credit -= amount;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public UUID getId() {
        return clientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addReservation(Reservation reservationCustom) {
        this.reservation = reservationCustom;
    }
}
