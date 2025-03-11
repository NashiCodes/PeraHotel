package org.perahotel.models;

import org.perahotel.observer.Observer;

import java.util.UUID;

public class Client extends Observer {
    private UUID clientId;
    private String name;
    private String email;
    private String phone;
    private Reservation reservation;

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

    public String pay() {
        var amount = reservation.Cost();
        return "Payment of " + amount + " was made";
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
