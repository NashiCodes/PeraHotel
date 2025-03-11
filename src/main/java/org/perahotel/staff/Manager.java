package org.perahotel.staff;

import org.perahotel.models.Hotel;
import org.perahotel.shared.Request;
import org.perahotel.staff.requests.NewFranchise;

import java.util.UUID;

public class Manager extends Employee {
    private final UUID token = UUID.randomUUID();
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
    public Request getRequestAcceptable() {
        return NewFranchise.getInstance();
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

    public void upgradeHotel(int capacity) {
        Hotel.getInstance().increaseHotelCapacity(capacity, this.getToken());
    }

    public UUID getToken() {
        return token;
    }

    @Override
    public String resolveRequest(Request request) {
        return "Manager will handle this request";
    }
}
