package org.perahotel.staff;

import org.perahotel.hotel.states.room.Maintenance;
import org.perahotel.models.Reservation;
import org.perahotel.models.Room;
import org.perahotel.shared.Request;
import org.perahotel.staff.requests.CleanRoom;
import org.perahotel.staff.requests.MaintenanceRequest;

public class Janitor extends Employee {
    private int roomsCleaned = 0;
    private int roomsMaintained = 0;

    public Janitor() {
        super();
    }

    @Override
    protected double calculateSalary() {
        var bonusPerRoom = this.getRoomsCleaned() * this.getBonus();
        return this.Salary + bonusPerRoom;
    }

    @Override
    public boolean validateRequest(Request request) {
        var isCleanRoom = request instanceof CleanRoom;
        var isMaintenance = request instanceof MaintenanceRequest;
        return isMaintenance || isCleanRoom;
    }

    @Override
    protected String getExtraInfo() {
        return "Rooms Cleaned: " + this.getRoomsCleaned() + "\n";
    }

    public int getRoomsCleaned() {
        return roomsCleaned;
    }

    public void setRoomsCleaned(int roomsCleaned) {
        this.roomsCleaned = roomsCleaned;
    }

    public void cleanRoom(Room room) {
        if (room.getState() != Maintenance.getInstance()) {
            throw new IllegalArgumentException("Room must be in maintenance state");
        }

        this.roomsCleaned++;
    }

    public void fixRoom(Room room) {
        if (room.getState() != Maintenance.getInstance()) {
            throw new IllegalArgumentException("Room must be in maintenance state");
        }

        this.roomsMaintained++;
        this.roomsCleaned++;
    }

    public int getRoomsMaintained() {
        return roomsMaintained;
    }

    public void setRoomsMaintained(int roomsMaintained) {
        this.roomsMaintained = roomsMaintained;
    }


    @Override
    public String resolveRequest(Request request, Reservation reservation) {
        if (request instanceof CleanRoom) {
            this.cleanRoom(reservation.getRoom());
            return "Janitor cleaned the room";
        }
        if (request instanceof MaintenanceRequest) {
            this.fixRoom(reservation.getRoom());
            return "Janitor fixed the room";
        }
        return "Janitor will handle this request";
    }
}
