package org.perahotel.staff;

import org.perahotel.hotel.decorators.reservation.BreakfastIncluded;
import org.perahotel.hotel.decorators.reservation.DinnerIncluded;
import org.perahotel.hotel.decorators.reservation.LunchIncluded;
import org.perahotel.hotel.decorators.reservation.SnacksIncluded;
import org.perahotel.hotel.decorators.room.*;
import org.perahotel.hotel.states.reservation.CheckedIn;
import org.perahotel.models.Client;
import org.perahotel.models.Hotel;
import org.perahotel.models.Reservation;
import org.perahotel.models.builders.ReservationBuilder;
import org.perahotel.shared.Request;
import org.perahotel.staff.requests.CleanRoom;
import org.perahotel.staff.requests.ReservationMade;

public class Secretary extends Employee {
    private int ReservationsMade = 0;

    public Secretary() {
        super();
    }


    public Reservation createReservation(Client client, int days) {
        var room = Hotel.getInstance().getAvailableRoom();

        var reservation = new ReservationBuilder()
                .setGuestId(client.getId())
                .setRoom(room)
                .setDays(days)
                .build();

        client.addReservation(reservation);

        return reservation;
    }

    public void reserve(Reservation reservation, Client client) {
        if (reservation.getGuestId() != client.getId()) {
            throw new IllegalArgumentException("Client must be the same as the one in the reservation");
        }
        if (client.getCredit() >= reservation.Cost()) {
            Hotel.getInstance().reserve(reservation);
            this.resolveRequest(ReservationMade.getInstance(), reservation);
        } else {
            reservation.Decline();
            throw new IllegalArgumentException("Client must have enough money to pay for the reservation");
        }

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
        if (reservation.getState() != CheckedIn.getInstance()) {
            throw new IllegalArgumentException("Client must be checked in to check out");
        }

        if (client.pay()) {
            Hotel.getInstance().checkOut(reservation);
            this.requestHandler(CleanRoom.getInstance(), reservation);
        } else
            throw new IllegalArgumentException("Client must have enough money to pay for the reservation");

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

    public void addDinner(Client client) {
        var reservation = client.getReservation();
        reservation = new DinnerIncluded(reservation);
        client.addReservation(reservation);
    }

    public void addBreakfast(Client client) {
        var reservation = client.getReservation();
        reservation = new BreakfastIncluded(reservation);
        client.addReservation(reservation);
    }

    public void addSnacks(Client client) {
        var reservation = client.getReservation();
        reservation = new SnacksIncluded(reservation);
        client.addReservation(reservation);
    }

    public void addLunch(Client client) {
        var reservation = client.getReservation();
        reservation = new LunchIncluded(reservation);
        client.addReservation(reservation);
    }

    public void addAllMeals(Client client) {
        var reservation = client.getReservation();
        reservation = new BreakfastIncluded(reservation);
        reservation = new LunchIncluded(reservation);
        reservation = new DinnerIncluded(reservation);
        reservation = new SnacksIncluded(reservation);
        client.addReservation(reservation);
    }

    public void addWifi(Client client) {
        var reservation = client.getReservation();
        var room = reservation.getRoom();
        room = new WifiRoom(room);
        reservation.setRoom(room);
        client.addReservation(reservation);
    }

    public void addKingSizeBed(Client client) {
        var reservation = client.getReservation();
        var room = reservation.getRoom();
        room = new KingSize(room);
        reservation.setRoom(room);
        client.addReservation(reservation);
    }

    public void addDoubleBathroom(Client client) {
        var reservation = client.getReservation();
        var room = reservation.getRoom();
        room = new DoubleBathroom(room);
        reservation.setRoom(room);
        client.addReservation(reservation);
    }

    public void addBalcony(Client client) {
        var reservation = client.getReservation();
        var room = reservation.getRoom();
        room = new Balcony(room);
        reservation.setRoom(room);
        client.addReservation(reservation);
    }

    public void addDoubleSingleBed(Client client) {
        var reservation = client.getReservation();
        var room = reservation.getRoom();
        room = new DoubleSingleBed(room);
        reservation.setRoom(room);
        client.addReservation(reservation);
    }

    public void addAllRoomFeatures(Client client) {
        var reservation = client.getReservation();
        var room = reservation.getRoom();
        room = new WifiRoom(room);
        room = new KingSize(room);
        room = new DoubleBathroom(room);
        room = new Balcony(room);
        room = new DoubleSingleBed(room);
        reservation.setRoom(room);
        client.addReservation(reservation);
    }

    public void cancelReservation(Reservation reservation, Client client) {
        if (reservation.getGuestId() != client.getId()) {
            throw new IllegalArgumentException("Client must be the same as the one in the reservation");
        }
        if (client.pay()) {
            Hotel.getInstance().cancelReservation(reservation);
        } else
            throw new IllegalArgumentException("Client must have enough money to pay for the reservation");


    }
}
