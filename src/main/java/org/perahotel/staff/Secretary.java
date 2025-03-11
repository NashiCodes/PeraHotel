package org.perahotel.staff;

import org.perahotel.models.Client;
import org.perahotel.models.Hotel;
import org.perahotel.models.Reservation;
import org.perahotel.models.Room;
import org.perahotel.models.builders.ReservationBuilder;
import org.perahotel.shared.Request;
import org.perahotel.staff.requests.ServicesInformation;

import java.util.UUID;

public class Secretary extends Employee {
    private final UUID validationCode = UUID.randomUUID();
    private int ReservationsMade = 0;

    public Secretary() {
        super();
    }

    public void requestReservation(Client client, Room room, int days) {
        var reservation = new ReservationBuilder(room, client.getId(), days, this.validationCode).build();
        client.addReservation(reservation);
    }

    public Room getAvailableRoom() {
        return Hotel.getInstance().getAvailableRoom();
    }

    public void reserve(Reservation reservation) {
        if (validateReservation(reservation)) {
            Hotel.getInstance().processReservation(reservation);
            this.ReservationsMade++;
        }

        reservation.Decline();
        throw new IllegalArgumentException("Reserve must be validated by the secretary that made the reservation");
    }

    public boolean validateReservation(Reservation reservation) {
        return reservation.getReservationValidatedBy().equals(this.validationCode);
    }

    public void checkClientIn(Client client, Reservation reservation) {
        if (reservation.getGuestId() != client.getId()) {
            throw new IllegalArgumentException("Client must be the same as the one in the reservation");
        }
        Hotel.getInstance().checkIn(reservation);

    }

    public void checkClientOut(Client client, Reservation reservation) {
        if (reservation.getGuestId() != client.getId()) {
            throw new IllegalArgumentException("Client must be the same as the one in the reservation");
        }
        Hotel.getInstance().checkOut(reservation);
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
    public Request getRequestAcceptable() {
        return ServicesInformation.getInstance();
    }

    public int getReservationsMade() {
        return ReservationsMade;
    }

    public void setReservationsMade(int reservationsMade) {
        ReservationsMade = reservationsMade;
    }

    @Override
    public String resolveRequest(Request request) {
        return "Secretary will handle this request";
    }
}
