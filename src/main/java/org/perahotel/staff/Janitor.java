package org.perahotel.staff;

import org.perahotel.hotel.states.room.Maintenance;
import org.perahotel.models.Hotel;
import org.perahotel.models.Reservation;
import org.perahotel.models.Room;
import org.perahotel.shared.Request;
import org.perahotel.staff.requests.CleanRoom;
import org.perahotel.staff.requests.MaintenanceRequest;

public class Janitor extends Employee {
    private int roomsCleaned = 0;

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

        var cleanedRoom = new Room(room.getNumber());
        cleanedRoom.setId(room.getId());
        cleanedRoom.setPrice(room.Cost());
        cleanedRoom.setState(room.getState());
        cleanedRoom.setLastMessage(room.getLastMessage());

        cleanedRoom.available();

        Hotel.getInstance().addCleanedRoom(cleanedRoom);

        this.roomsCleaned++;
    }


    @Override
    public String resolveRequest(Request request, Reservation reservation) {
        if (request instanceof CleanRoom) {
            this.cleanRoom(reservation.getRoom());
            this.next.requestHandler(request, reservation);
            return "Janitor cleaned the room";
        }
        return "Janitor will handle this request";
    }
}
