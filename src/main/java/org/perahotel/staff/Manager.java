package org.perahotel.staff;

import org.perahotel.models.Reservation;
import org.perahotel.shared.Request;
import org.perahotel.staff.requests.CleanRoom;
import org.perahotel.staff.requests.NewFranchise;
import org.perahotel.staff.requests.ReservationMade;

public class Manager extends Employee {
    private int reservations = 0;
    private int roomsCleaned = 0;

    public Manager() {
        super();
    }

    @Override
    protected double calculateSalary() {
        var additional = this.getReservations() + this.getRoomsCleaned();
        var bonusTotal = additional * this.getBonus();
        return this.Salary + bonusTotal;
    }

    @Override
    public boolean validateRequest(Request request) {
        var isFranchise = request instanceof NewFranchise;
        var isReservation = request instanceof ReservationMade;
        var isCleanRoom = request instanceof CleanRoom;
        return isFranchise || isReservation || isCleanRoom;
    }

    @Override
    protected String getExtraInfo() {
        return "All reservations made in the hotel: " + this.getReservations() + "\n";
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public int getRoomsCleaned() {
        return roomsCleaned;
    }

    public void setRoomsCleaned(int roomsCleaned) {
        this.roomsCleaned = roomsCleaned;
    }

    public void updateRoomsCleaned() {
        this.roomsCleaned++;
    }

    public void updateReservations() {
        this.reservations++;
    }

    @Override
    public String resolveRequest(Request request, Reservation reservation) {
        if (request instanceof ReservationMade) {
            this.updateReservations();
        }
        if (request instanceof NewFranchise) {
            return "Manager will open a new franchise";
        }
        if (request instanceof CleanRoom) {
            this.updateRoomsCleaned();
        }
        return "Manager will handle this request";
    }
}
