package org.perahotel.models;

import org.perahotel.hotel.states.room.*;
import org.perahotel.observer.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel extends Observable {
    private static final Hotel instance = new Hotel().initialize();
    private int MAX_ROOMS = 10;
    private Map<RoomState, List<Room>> Rooms;

    private Hotel() {
    }

    public static Hotel getInstance() {
        return instance;
    }

    private Hotel initialize() {
        ConfigureHotelStorage();
        initRooms();
        return this;
    }

    private void ConfigureHotelStorage() {
        this.Rooms = new HashMap<>();
        this.Rooms.put(Available.getInstance(), new ArrayList<>());
        this.Rooms.put(Maintenance.getInstance(), new ArrayList<>());
        this.Rooms.put(Occupied.getInstance(), new ArrayList<>());
        this.Rooms.put(Reserved.getInstance(), new ArrayList<>());
    }

    private void initRooms() {
        for (int i = 0; i < MAX_ROOMS; i++) {
            var room = new Room(String.valueOf(i));
            this.Rooms.get(Available.getInstance()).add(room);
            this.addObserver(room);
        }
    }

    private void createRoom() {
        var room = new Room(String.valueOf(MAX_ROOMS));
        this.Rooms.get(Available.getInstance()).add(room);
        this.addObserver(room);
        MAX_ROOMS++;
    }

    private boolean hasRoomAvailable() {
        var AvailableRooms = Rooms.get(Available.getInstance());
        return !AvailableRooms.isEmpty();
    }

    public Room getAvailableRoom() {
        if (hasRoomAvailable()) {
            return Rooms.get(Available.getInstance()).getFirst();
        }
        createRoom();
        return getAvailableRoom();
    }

    public void updateBaseRoomsPrice(double price) {
        var allRooms = getRooms();
        for (var room : allRooms) {
            room.setPrice(price);
        }

        notifyObservers("Price updated to " + price);
    }

    public List<Room> getRooms() {
        var availableRooms = this.Rooms.get(Available.getInstance());
        var occupiedRooms = this.Rooms.get(Occupied.getInstance());
        var reservedRooms = this.Rooms.get(Reserved.getInstance());
        var maintenanceRooms = this.Rooms.get(Maintenance.getInstance());
        var rooms = new ArrayList<Room>();
        rooms.addAll(availableRooms);
        rooms.addAll(occupiedRooms);
        rooms.addAll(reservedRooms);
        rooms.addAll(maintenanceRooms);
        return rooms;
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
        this.Rooms.get(Maintenance.getInstance()).add(room);
        reservation.CheckOut();
    }

    public void addCleanedRoom(Room room) {
        var maintenanceRooms = this.Rooms.get(Maintenance.getInstance());
        maintenanceRooms.removeIf(r -> r.getId().equals(room.getId()));
        this.Rooms.get(Available.getInstance()).add(room);
    }
}
