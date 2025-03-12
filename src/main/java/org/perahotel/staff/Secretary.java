package org.perahotel.staff;

import org.perahotel.models.Client;
import org.perahotel.models.Hotel;
import org.perahotel.models.Reservation;
import org.perahotel.models.Room;
import org.perahotel.models.builders.ReservationBuilder;
import org.perahotel.shared.Request;
import org.perahotel.staff.requests.ReservationMade;

public class Secretary extends Employee {
    private int ReservationsMade = 0;

    public Secretary() {
        super();
    }

    public void requestReservation(Client client, Room room, int days) {
        var reservation = new ReservationBuilder(room, client.getId(), days).build();
        client.addReservation(reservation);
    }


    public Reservation createReservation(Client client, int days) {
        return new ReservationBuilder().setGuestId(client.getId()).setDays(days).build();
    }

    public void reserve(Reservation reservation, Client client) {
        if (reservation.getGuestId() != client.getId()) {
            throw new IllegalArgumentException("Client must be the same as the one in the reservation");
        }
        if (client.pay()) {
            Hotel.getInstance().reserve(reservation);
            this.resolveRequest(ReservationMade.getInstance(), reservation);
        } else {
            reservation.Decline();
            throw new IllegalArgumentException("Client must have enough money to pay for the reservation");
        }

    }


    public void validateReservation(Reservation reservation) {
        reservation.Confirm();
    }


    public void checkClientIn(Client client, Reservation reservation) {
        if (reservation.getGuestId() != client.getId()) {
            throw new IllegalArgumentException("Client must be the same as the one in the reservation");
        }

    }

    @Override
    protected String getExtraInfo() {
        return "All reservations made: " + this.getReservationsMade() + "\n";
    }

    @Override
    protected double calculateSalary() {
        var bonusPerReservation = this.getReservationsMade() * this.getBonus();
        return this.Salary + bonusPerReservation;
    }

    @Override
    public boolean validateRequest(Request request) {
        return request instanceof ReservationMade;
    }

    public int getReservationsMade() {
        return ReservationsMade;
    }

    public void setReservationsMade(int reservationsMade) {
        ReservationsMade = reservationsMade;
    }

    public void updateReservationsMade() {
        this.ReservationsMade++;
    }

    @Override
    public String resolveRequest(Request request, Reservation reservation) {
        if (request instanceof ReservationMade) {
            this.updateReservationsMade();
            this.next.requestHandler(ReservationMade.getInstance(), reservation);

            return "Secretary Handled this request";
        }
        return "";
    }
}
