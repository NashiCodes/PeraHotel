package org.perahotel.models;

import org.perahotel.hotel.states.room.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    private static final Hotel instance = new Hotel().initialize();
    private final int MAX_ROOMS = 10;
    private Map<RoomState, List<Room>> Rooms;

    private Hotel() {
    }

    public static Hotel getInstance() {
        return instance;
    }

    private Hotel initialize() {
        ConfigureHotelStorage();
        createRooms();
        return this;
    }

    private void ConfigureHotelStorage() {
        this.Rooms = new HashMap<>();
        this.Rooms.put(Available.getInstance(), new ArrayList<>());
        this.Rooms.put(Maintenance.getInstance(), new ArrayList<>());
        this.Rooms.put(Occupied.getInstance(), new ArrayList<>());
        this.Rooms.put(Reserved.getInstance(), new ArrayList<>());
    }

    private void createRooms() {
        for (int i = 0; i < MAX_ROOMS; i++) {
            var room = new Room(String.valueOf(i));
            this.Rooms.get(Available.getInstance()).add(room);
        }
    }

    private boolean hasRoomAvailable() {
        var AvailableRooms = Rooms.get(Available.getInstance());
        return !AvailableRooms.isEmpty();
    }

    public Room getAvailableRoom() {
        if (hasRoomAvailable()) {
            return Rooms.get(Available.getInstance()).getFirst();
        }
        createRooms();
        return getAvailableRoom();
    }

    public void checkIn(Reservation reservation) {
        reservation.CheckIn();
        this.Rooms.get(Reserved.getInstance()).remove(reservation.getRoom());
        this.Rooms.get(Occupied.getInstance()).add(reservation.getRoom());
    }

    public void reserve(Reservation reservation) {
        var room = reservation.getRoom();
        if (this.Rooms.get(Reserved.getInstance()).contains(room)) {
            throw new IllegalArgumentException("Someone already reserved this room");
        }
        reservation.Confirm();
        var roomId = room.getId();
        var AvailableRooms = this.Rooms.get(Available.getInstance());
        AvailableRooms.removeIf(r -> r.getId().equals(roomId));
        this.Rooms.get(Reserved.getInstance()).add(room);
    }

    public void cancelReservation(Reservation reservation) {
        var room = reservation.getRoom();
        this.Rooms.get(room.getState()).remove(room);
        reservation.Cancel();
        this.Rooms.get(Available.getInstance()).add(room);
    }

    public void checkOut(Reservation reservation) {
        var room = reservation.getRoom();
        this.Rooms.get(room.getState()).remove(room);
        reservation.CheckOut();
    }
}
