package org.perahotel.staff;

import org.perahotel.models.Room;
import org.perahotel.shared.Request;
import org.perahotel.staff.requests.WaterLeakage;

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
    public Request getRequestAcceptable() {
        return WaterLeakage.getInstance();
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
        room.available();
        this.roomsCleaned++;
    }

    @Override
    public String resolveRequest(Request request) {
        return "Janitor will handle this request";
    }
}
